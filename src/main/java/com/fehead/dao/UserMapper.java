package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.dao.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 16:11
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select id,nickname,tel,password_id from user_info where nickname=#{username}")
    public User selectByUsername(String username);
    @Select("select * from user_info where tel=#{tel}")
    public User selectByUserTel(String tel);
}
