package com.vr.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vr.rental.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRoleByUserIds(@Param("userIds") Collection<Long> userIds);

}
