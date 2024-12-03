package nl.fontys.TaskBuddy.persistence.interf;

import nl.fontys.TaskBuddy.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> getTaskEntitiesByUserId(Long userId);
}
