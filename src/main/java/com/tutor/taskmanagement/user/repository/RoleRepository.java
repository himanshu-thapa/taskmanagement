package com.tutor.taskmanagement.user.repository;

import com.tutor.taskmanagement.user.enitites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String user);
}
