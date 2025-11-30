package com.vr.rental.mapping;


import com.vr.rental.domain.dto.UserCreateDTO;
import com.vr.rental.domain.dto.UserUpdateDTO;
import com.vr.rental.domain.entity.User;
import com.vr.rental.domain.vo.UserVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true),uses = {LocalDateTimeMapper.class,LocalDateMapper.class})
public interface UserMapping {

    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);


    User toUser(UserCreateDTO userCreateDTO);

    //部分更新
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    //全量更新
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void overwriteUser(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    UserVO toUserVO(User user);

    @IterableMapping(elementTargetType = UserVO.class)
    List<UserVO> toUserVO(List<User> users);

}
