package cn.skuu.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author dcx
 * @since 2022-09-18 17:38
 **/
@Data
@ApiModel("用户对象")
public class UserDto {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户头像")
    private String avatar;
}
