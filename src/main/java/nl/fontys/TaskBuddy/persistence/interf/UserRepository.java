package nl.fontys.TaskBuddy.persistence.interf;

import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}