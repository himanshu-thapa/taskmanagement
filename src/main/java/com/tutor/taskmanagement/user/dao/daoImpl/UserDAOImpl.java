package com.tutor.taskmanagement.user.dao.daoImpl;

import com.tutor.taskmanagement.user.dao.UserDAO;
import com.tutor.taskmanagement.user.enitites.User;
import com.tutor.taskmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findUserByResetToken(String token) {
        return userRepo.findByResetToken(token);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> user1 = userRepo.findById(user.getId());
        if (user1.isPresent()) {
            User user2 = user1.get();
            user2.setPassword(user.getPassword());
            userRepo.save(user2);
            return user2;
        }
        return null;
    }
}
