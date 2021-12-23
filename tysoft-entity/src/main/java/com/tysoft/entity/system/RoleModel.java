package com.tysoft.entity.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: RoleModel
 * @Author: hxx
 * @Date: 2021/12/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色对象Bean")
public class RoleModel implements Serializable {

    @ApiModelProperty(value = "主键")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色标题")
    private String roleTitle;

    @ApiModelProperty(value = "状态(0:可用,1:不可用)")
    private String state;

    @ApiModelProperty(value = "描述", hidden = true)
    private String description;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private String updateTime;


}
