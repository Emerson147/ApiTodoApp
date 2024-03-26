package com.emersondev.persistence.repository;

import com.emersondev.persistence.entity.Task;
import com.emersondev.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

  //LISTA LOS ITEMS POR STATUS
  public List<Task> findAllByTaskStatus(TaskStatus status);

  // UPDATE -> ACTUALIZA
  @Modifying
  @Query(value = "update Task set finished = true where id =:id", nativeQuery = true)
  public void markTaskAsFinished(@Param("id") Long id);

}
