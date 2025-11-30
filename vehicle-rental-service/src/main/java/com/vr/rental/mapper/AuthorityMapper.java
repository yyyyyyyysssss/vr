package com.vr.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vr.common.mybatis.mapper.TreeMapper;
import com.vr.rental.domain.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority>, TreeMapper<Authority> {

    List<Authority> findMenuByRoleIds(@Param("roleIds") Collection<Long> roleIds);

}
