package com.tutor.taskmanagement.user.dao;

import com.tutor.taskmanagement.user.enitites.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDAO {
    List<User> findAllUsers();

    User findUserById(Long id);

    User createUser(User user);

    void deleteUser(Long id);

    User findUserByEmail(String email);

    User findUserByResetToken(String token);

    User updateUser(User user);
}
