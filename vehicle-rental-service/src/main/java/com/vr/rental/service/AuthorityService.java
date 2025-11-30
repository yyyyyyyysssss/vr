package com.vr.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vr.rental.domain.dto.AuthorityCreateDTO;
import com.vr.rental.domain.dto.AuthorityUpdateDTO;
import com.vr.rental.domain.entity.Authority;
import com.vr.rental.domain.vo.AuthorityVO;

import java.util.List;

public interface AuthorityService extends IService<Authority> {

    Long createAuthority(AuthorityCreateDTO authorityAddDTO);

    Boolean updateAuthority(AuthorityUpdateDTO authorityUpdateDTO, Boolean isFullUpdate);

    AuthorityVO details(String id);

    List<AuthorityVO> tree();

    Boolean deleteAuthority(Long id);

    List<AuthorityVO> findByRoleId(Long roleId);

    List<AuthorityVO> findByUserId(Long userId);
}
