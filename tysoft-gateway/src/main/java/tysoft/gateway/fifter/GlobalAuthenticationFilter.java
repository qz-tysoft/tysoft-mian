package tysoft.gateway.fifter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tysoft.entity.system.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tysoft.gateway.redis.RedisUtil;
import tysoft.util.jwt.JWTToken;
import tysoft.util.jwt.TokenConstants;
import tysoft.util.response.ResponseEnum;
import tysoft.util.response.ServerResponse;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * gateway 全局过滤器 过滤Token
 *
 * @author hxx
 * @date 2022-03-05
 */
@Slf4j
@Component
public class GlobalAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    RedisUtil redisUtil;

    static String[] whiteUrl = {"/auth/login/userLogin"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        // 存在直接放行
        boolean isNext = true;
        for (String url : whiteUrl) {
            if (url.equals(requestUrl)) {
                isNext = false;
                break;
            }
        }

        if(!isNext) {
            return chain.filter(exchange);
        }

        String token = getToken(exchange);
        if (token == null) {
            log.info("Token不为空");
            return invalidTokenMono(exchange, ResponseEnum.NOT_TOKEN.getCode());
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TokenConstants.SING_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            // 获取用户名
            String userId = claims.getId();
            Date expireDate = claims.getExpiration();
            Date nowDate = new Date();
            // 判断redis中token是否过期
            String redisToken = (String) redisUtil.get(userId);
            long subTime = expireDate.getTime() - nowDate.getTime();
            // token差5分钟过期，刷新refreshToken
            GateWayVO result = new GateWayVO();
            if (StringUtils.isNotBlank(redisToken) && subTime < 5 * 60 * 1000) {
                // 建立json对象、
                UserModel refreshModel = new UserModel();
                refreshModel.setUserId(userId);
                refreshModel.setAccount(claims.getSubject());
                refreshModel.setAccount(claims.getSubject());
                String authority = claims.get("authorities").toString();
                Set<GrantedAuthority> authorities = new HashSet<>();
                if (!StringUtils.isEmpty(authority)) {
                    List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                    authorityMap.forEach(
                            role -> authorities.add(new SimpleGrantedAuthority(role.get("authority")))
                    );
                }
                refreshModel.setAuthorities(authorities);
                String refreshToken = JWTToken.createAccessToken(refreshModel);
                result.setRefreshToken(refreshToken);
                result.setUserId(userId);
                redisUtil.set(userId, refreshToken);
                redisUtil.expire(userId, TokenConstants.EXPIRE_TIME_LONG);
            } else {
                result.setUserId(userId);
            }
            ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header(TokenConstants.TOKEN_DATA_FIELD, "{\"userId\":\""+result.getUserId()+"\",\"refreshToken\":\""+result.getRefreshToken()+"\"}").build();
            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
            return chain.filter(build);
        } catch (ExpiredJwtException e) {
            log.info("Token过期");
            return invalidTokenMono(exchange, ResponseEnum.TOKEN_EXPIRE.getCode());
        } catch (Exception e) {
            log.info("Token无效");
            return invalidTokenMono(exchange, ResponseEnum.TOKEN_NO_LEGAL.getCode());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }


    /**
     * 从请求头中获取Token
     */
    private String getToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }

    /**
     * token校验
     */
    private Mono<Void> invalidTokenMono(ServerWebExchange exchange, Integer status) {
        Mono<Void> result = null;
        if (ResponseEnum.NOT_TOKEN.getCode().equals(status)) {
            result = buildReturnMono(ServerResponse.noToken(), exchange);
        } else if (ResponseEnum.TOKEN_EXPIRE.getCode().equals(status)) {
            result = buildReturnMono(ServerResponse.tokenExpire(), exchange);
        } else if (ResponseEnum.TOKEN_NO_LEGAL.getCode().equals(status)) {
            result = buildReturnMono(ServerResponse.tokenNoLegal(), exchange);
        }
        return result;
    }


    /**
     * 过滤链通用返回
     *
     * @param resultMsg
     * @param exchange
     * @return
     */
    private Mono<Void> buildReturnMono(ServerResponse resultMsg, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = JSON.toJSONString(resultMsg).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset:utf-8");
        return response.writeWith(Mono.just(buffer));
    }
}
