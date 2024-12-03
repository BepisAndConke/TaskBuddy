package nl.fontys.TaskBuddy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@EqualsAndHashCode
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 3, max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 3, max = 75)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Length(min = 3, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks = new ArrayList<>();

    @NotNull
    private Integer exp;

    @NotNull
    private Integer level;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<AccountEntity> accounts;
//
//    @OneToMany(mappedBy = "user")
//    private List<FriendshipEntity> friendships;
}
