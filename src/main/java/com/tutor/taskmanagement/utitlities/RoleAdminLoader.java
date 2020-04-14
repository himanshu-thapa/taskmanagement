package com.tutor.taskmanagement.utitlities;

import com.tutor.taskmanagement.user.enitites.Role;
import com.tutor.taskmanagement.user.enitites.User;
import com.tutor.taskmanagement.user.repository.RoleRepository;
import com.tutor.taskmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Configuration
@ConditionalOnProperty(name = "role.admin.create.populate", havingValue = "true")
@Transactional
public class RoleAdminLoader implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//Creating roles

        List<Role> roles = Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN"));
        roleRepo.saveAll(roles);

//Create Admins

        Role adminRole = roleRepo.findByRole("ROLE_ADMIN");
        List<User> admins = Arrays.asList(
                new User("Himanshu", "Thapa", "himanshu@admin.com",
                        passwordEncoder.encode("password"), "9849685382",
                        new Date(), new Date(),
                        new HashSet<>(Collections.singletonList(adminRole))),
                new User("Shaubhagya", "Kshetri", "shaubhagya@admin.com",
                        passwordEncoder.encode("password"), "9849685382",
                        new Date(), new Date(),
                        new HashSet<>(Collections.singletonList(adminRole)))
        );
        userRepo.saveAll(admins);
    }
}
