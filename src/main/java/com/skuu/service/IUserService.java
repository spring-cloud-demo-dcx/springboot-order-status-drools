package com.skuu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.skuu.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
public interface IUserService extends IService<User> {

    User getUser(String userId);

}
