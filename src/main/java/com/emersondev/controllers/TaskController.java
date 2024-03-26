package com.emersondev.controllers;

import com.emersondev.persistence.entity.Task;
import com.emersondev.persistence.entity.TaskStatus;
import com.emersondev.service.DTO.TaskInDTO;
import com.emersondev.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/create")
  public Task createTask(@RequestBody TaskInDTO taskInDTO) {
    return this.taskService.createTask(taskInDTO);
  }

  @GetMapping
  public List<Task> findAll() {
    return this.taskService.findAll();
  }

  @GetMapping("/status/{status}")
  public List<Task> findAllByStatus(@PathVariable("status")TaskStatus status){
    return this.taskService.findAllByTaskStatus(status);
  }

  @PatchMapping("/mark_as_finished/{id}")
  public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
    this.taskService.updateTaskAsFinished(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    this.taskService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
