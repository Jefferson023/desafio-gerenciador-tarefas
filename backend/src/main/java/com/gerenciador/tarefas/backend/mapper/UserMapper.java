package com.gerenciador.tarefas.backend.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.gerenciador.tarefas.backend.dto.AuthRequestDto;
import com.gerenciador.tarefas.backend.entity.User;

import lombok.RequiredArgsConstructor;

@Component("userMapper")
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;
    
    public User mapTo(AuthRequestDto authReqDto) {
    	TypeMap<AuthRequestDto, User> typeMap = mapper.
    			createTypeMap(AuthRequestDto.class, User.class);
    	typeMap.addMapping(AuthRequestDto::getLogin, User::setUsername);
        return typeMap.map(authReqDto);
    }
}
