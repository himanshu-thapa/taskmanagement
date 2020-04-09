package com.tutor.taskmanagement.user;

import com.tutor.taskmanagement.user.dto.UserDTO;
import com.tutor.taskmanagement.user.enitites.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);

    }

}
