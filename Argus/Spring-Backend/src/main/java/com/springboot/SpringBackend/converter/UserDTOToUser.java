package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.UserDTO;
import com.springboot.SpringBackend.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserDTOToUser implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO dto) {
        User x = new User();
        if (dto.getUserId() != null  && !StringUtils.isEmpty(dto.getUserId())) {
            x.setUserId(dto.getUserId());
        }
        x.setName(dto.getName());
        x.setEmail(dto.getEmail());
        x.setUsername(dto.getUsername());
        x.setPassword(dto.getPassword());
        x.setRole(dto.getRole());
        x.setProfilePhoto(dto.getProfilePhoto());
        return x;
    }
}
