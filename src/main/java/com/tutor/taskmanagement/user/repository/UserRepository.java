package com.tutor.taskmanagement.user.repository;

import com.tutor.taskmanagement.user.enitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByResetToken(String token);
}
