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
}
