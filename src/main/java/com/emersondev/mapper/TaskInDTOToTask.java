package com.emersondev.mapper;

import com.emersondev.persistence.entity.Task;
import com.emersondev.persistence.entity.TaskStatus;
import com.emersondev.service.DTO.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {
  @Override
  public Task map(TaskInDTO in) {
    Task task = new Task();
    task.setTitle(in.getTitle());
    task.setDescription(in.getDescription());
    task.setEta(in.getEta());
    task.setCreateDate(LocalDateTime.now());
    task.setFinished(false);

    if ("LATE".equalsIgnoreCase(String.valueOf(in.getTaskStatus()))) {
      task.setTaskStatus(TaskStatus.LATE);
    } else if ("ON_TIME".equalsIgnoreCase(String.valueOf(in.getTaskStatus()))) {
      task.setTaskStatus(TaskStatus.ON_TIME);
    }

    return task;
  }
}
