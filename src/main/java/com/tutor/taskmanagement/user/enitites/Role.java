package com.tutor.taskmanagement.user.enitites;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String role;

    /*@OneToOne(mappedBy = "role")
    private User user;*/

    /*@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users;*/

    public Role(String role) {
        this.role = role;
    }
}
