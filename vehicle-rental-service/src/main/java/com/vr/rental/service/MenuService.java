package com.vr.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.vr.rental.domain.dto.MenuCreateDTO;
import com.vr.rental.domain.dto.MenuDragDTO;
import com.vr.rental.domain.dto.MenuQueryDTO;
import com.vr.rental.domain.dto.MenuUpdateDTO;
import com.vr.rental.domain.entity.Authority;
import com.vr.rental.domain.vo.MenuVO;

import java.util.Collection;
import java.util.List;

public interface MenuService extends IService<Authority> {

    Long createMenu(MenuCreateDTO menuCreateDTO);

    Integer updateMenu(MenuUpdateDTO menuUpdateDTO);

    Boolean deleteMenu(Long id);

    Boolean menuDrag(MenuDragDTO menuDragDTO);

    List<MenuVO> tree();

    PageInfo<MenuVO> query(MenuQueryDTO menuQueryDTO);

    MenuVO details(Long id);

    List<MenuVO> findByUserId(Long userId);

    List<MenuVO> findByUserId(Long userId, Collection<Long> roleIds);
}
