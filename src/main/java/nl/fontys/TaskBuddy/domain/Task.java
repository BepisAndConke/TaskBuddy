package nl.fontys.TaskBuddy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Integer rewardXp;
    private Integer difficulty;
    private Integer length;
    private Date createdAt;
    private Date dueDate;
    private Long userId;
}
