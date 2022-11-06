package com.skuu.adapter;

import com.skuu.pojo.vo.UserVo;
import com.skuu.entity.User;
import com.skuu.pojo.dto.UserDto;
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
