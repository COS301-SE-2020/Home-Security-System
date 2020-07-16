package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.UserDTO;
import com.springboot.SpringBackend.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{
    @Override
    public UserDTO convert(User x)
    {
        UserDTO dto = new UserDTO();
        dto.setUserId(x.getUserId());
        dto.setName(x.getName());
        dto.setEmail(x.getEmail());
        dto.setUsername(x.getUsername());
        dto.setPassword(x.getPassword());
        dto.setRole(x.getRole());
        dto.setProfilePhoto(x.getProfilePhoto());
        return dto;
    }
}
