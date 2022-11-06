package cn.skuu.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dcx
 * @since 2022-09-18 18:35
 **/
@Data
@ApiModel("用户查询")
public class UserQuery {
    @ApiModelProperty(value = "用户id", required = true)
    @NotBlank
    private String userId;
    @ApiModelProperty(value = "房间id", required = true)
    @NotBlank
    private String roomId;
}
