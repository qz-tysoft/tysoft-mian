package com.tysoft.entity.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: RolePermissionModel
 * @Author: hxx
 * @Date: 2021/12/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色权限Bean")
public class RolePermissionModel implements Serializable {


    @ApiModelProperty(value = "主键", hidden = true)
    private String rolePermissionId;

    @ApiModelProperty(value = "角色主键", hidden = true)
    private Integer roleId;

    @ApiModelProperty(value = "权限主键", hidden = true)
    private Integer permissionId;


}
