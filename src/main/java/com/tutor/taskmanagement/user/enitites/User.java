package com.tutor.taskmanagement.user.enitites;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Setter(AccessLevel.NONE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Date createdDate;
    private Date updatedDate;

    private String resetToken;


    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private Role role;*/

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User(String firstName, String lastName, String email, String password,
                String phone, Date createdDate, Date updatedDate, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.roles = roles;
    }
}
