package com.vr.rental.mapping;

import com.vr.rental.domain.dto.RoleCreateDTO;
import com.vr.rental.domain.dto.RoleUpdateDTO;
import com.vr.rental.domain.entity.Role;
import com.vr.rental.domain.vo.RoleVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true),uses = {LocalDateTimeMapper.class,LocalDateMapper.class})
public interface RoleMapping {

    RoleMapping INSTANCE = Mappers.getMapper(RoleMapping.class);


    Role toRole(RoleCreateDTO roleCreateDTO);

    //部分更新
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRole(RoleUpdateDTO roleUpdateDTO, @MappingTarget Role role);

    //全量更新
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void overwriteRole(RoleUpdateDTO roleUpdateDTO, @MappingTarget Role Role);

    RoleVO toRoleVO(Role role);

    @IterableMapping(elementTargetType = RoleVO.class)
    List<RoleVO> toRoleVO(List<Role> roles);

}
