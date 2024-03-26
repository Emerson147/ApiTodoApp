package com.emersondev.service;

import com.emersondev.exceptions.ToDoExceptions;
import com.emersondev.mapper.TaskInDTOToTask;
import com.emersondev.persistence.entity.Task;
import com.emersondev.persistence.entity.TaskStatus;
import com.emersondev.persistence.repository.TaskRepository;
import com.emersondev.service.DTO.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
  private final TaskRepository repository;
  private final TaskInDTOToTask mapper;

  public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public Task createTask(TaskInDTO taskInDTO) {
    Task task = mapper.map(taskInDTO);
    return this.repository.save(task);
  }

  public List<Task> findAll() {
    return this.repository.findAll();
  }

  public List<Task> findAllByTaskStatus(TaskStatus status) {
    return this.repository.findAllByTaskStatus(status);
  }

  @Transactional
  public void updateTaskAsFinished(Long id) {
    Optional<Task> optionalTask = this.repository.findById(id);
    if (optionalTask.isEmpty()) {
      throw new ToDoExceptions("Task not found mi rey", HttpStatus.NOT_FOUND);
    }
    this.repository.markTaskAsFinished(id);
  }

  public void deleteById(Long id) {
    Optional<Task> optionalTask = this.repository.findById(id);
    if (optionalTask.isEmpty()) {
      throw new ToDoExceptions("Task not found rey precioso", HttpStatus.NOT_FOUND);
    }
    this.repository.deleteById(id);
  }
}
