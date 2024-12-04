package nl.fontys.TaskBuddy.domain.requests.tasks;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long taskId;

    private String title;

    private String description;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer difficulty;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer length;

    private String status;

    private String dueDate;
}
