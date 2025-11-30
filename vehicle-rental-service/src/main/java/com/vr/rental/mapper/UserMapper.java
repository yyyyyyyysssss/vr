package com.vr.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vr.rental.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
