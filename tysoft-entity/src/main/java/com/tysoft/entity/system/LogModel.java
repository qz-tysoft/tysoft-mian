package com.tysoft.entity.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hxx
 * @date 2021/12/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "系统日志")
public class LogModel implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "操作人员")
    private String optUserName;

    @ApiModelProperty(value = "操作人员Id")
    private String optUserId;

    @ApiModelProperty(value = "操作时间")
    private String createTime;

    @ApiModelProperty(value = "操作备注")
    private String optRemark;

    @ApiModelProperty(value = "操作方法")
    private String optMethod;

    @ApiModelProperty(value = "操作字段")
    private String params;

}
