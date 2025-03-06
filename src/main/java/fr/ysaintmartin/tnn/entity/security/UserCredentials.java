package fr.ysaintmartin.tnn.entity.security;

import fr.ysaintmartin.tnn.enumeration.security.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class UserCredentials implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private LocalDateTime lastLoginDate;

}
