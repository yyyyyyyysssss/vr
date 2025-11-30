package com.vr.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vr.rental.domain.entity.Authority;
import com.vr.rental.mapper.AuthorityMapper;
import jakarta.annotation.Resource;

import java.io.Serializable;

/**
 * @Description
 * @Author ys
 * @Date 2025/5/30 11:09
 */
public abstract class AbstractAuthorityService extends ServiceImpl<AuthorityMapper, Authority>{

    @Resource
    protected AuthorityMapper authorityMapper;


    public int getMinSortOfChildren(Serializable id, int defaultSort) {
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("Min(sort) as sort");
        queryWrapper.eq("parent_id",id);
        Authority authority = authorityMapper.selectOne(queryWrapper);
        return authority != null ? authority.getSort() : defaultSort;
    }

    public int getMaxSortOfChildren(Serializable id){
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("Max(sort) as sort");
        queryWrapper.eq("parent_id",id);
        Authority authority = authorityMapper.selectOne(queryWrapper);
        return authority != null ? authority.getSort() : 0;
    }


}
