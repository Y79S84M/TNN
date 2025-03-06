package fr.ysaintmartin.tnn.repository.security;

import fr.ysaintmartin.tnn.entity.security.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, Long> {

    @Query("""
                    SELECT CASE WHEN COUNT(u) = 1 THEN TRUE ELSE FALSE END
                    FROM UserCredentials u
                    WHERE u.username = :username
            """)
    boolean existsByUsername(String username);

    Optional<UserCredentials> findByUsername(String username);
}
