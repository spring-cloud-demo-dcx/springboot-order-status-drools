package cn.skuu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
@Data
@Builder
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String userName;

    private String avatar;

    private Integer status;

    private String roomId;
    private LocalDateTime updateTime;

    @Tolerate
    public User() {
    }
}
