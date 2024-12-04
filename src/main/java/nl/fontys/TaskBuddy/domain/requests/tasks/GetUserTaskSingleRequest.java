package nl.fontys.TaskBuddy.domain.requests.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTaskSingleRequest {
    private Long userId;
    private Long taskId;
}
