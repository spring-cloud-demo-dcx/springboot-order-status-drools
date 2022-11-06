package com.skuu.mapper;

import com.skuu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
