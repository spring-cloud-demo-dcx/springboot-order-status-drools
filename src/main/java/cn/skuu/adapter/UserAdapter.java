package cn.skuu.adapter;

import cn.skuu.pojo.vo.UserVo;
import cn.skuu.entity.User;
import cn.skuu.pojo.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 *
 * @author dcx
 * @since 2022-08-30 23:30
 **/
@Mapper(componentModel = "spring")
public interface UserAdapter {

    UserVo userToUserVo(User user);

    List<User> userDtosToUsers(List<UserDto> inviters);

    List<UserVo> usersToUserVos(List<User> users);
}
