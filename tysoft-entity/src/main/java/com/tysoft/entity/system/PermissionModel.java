package com.tysoft.entity.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: PermissionModel
 * @Author: hxx
 * @Date: 2021/12/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "权限对象Bean")
public class PermissionModel implements Serializable {

    @ApiModelProperty(value = "用户主键")
    private String permissionId;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限值")
    private String permissionValue;

    @ApiModelProperty(value = "权限类型(0:目录,1:菜单,2:按钮)")
    private String permissionType;

    @ApiModelProperty(value = "权限状态(0:可用,1:不可用)")
    private String permissionState;

    @ApiModelProperty(value = "上级编号")
    private String superiorId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;
}
