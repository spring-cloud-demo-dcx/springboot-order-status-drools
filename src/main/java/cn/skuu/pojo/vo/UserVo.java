package cn.skuu.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
@Data
@ApiModel("用户信息")
public class UserVo {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户状态：0:在线1:被邀请等待2:主动邀请中3:通话中4:离线5:踢出")
    private Integer status;
    @ApiModelProperty("房间id")
    private String roomId;
    @ApiModelProperty("用户头像")
    private String avatar;

}
