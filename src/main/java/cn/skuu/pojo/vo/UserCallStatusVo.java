package cn.skuu.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
@Data
@ApiModel("用户通话状态")
public class UserCallStatusVo {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户状态：0:准备通话中:3:通话中4:离线5:踢出")
    private Integer callStatus;
    @ApiModelProperty("房间id")
    private String roomId;
}
