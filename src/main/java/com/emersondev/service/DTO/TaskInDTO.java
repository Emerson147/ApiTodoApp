package com.emersondev.service.DTO;

import com.emersondev.persistence.entity.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskInDTO {
  private String title;
  private String description;
  private LocalDateTime eta;
  private TaskStatus taskStatus;
}
