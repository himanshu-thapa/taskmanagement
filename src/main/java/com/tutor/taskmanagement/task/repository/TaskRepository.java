package com.tutor.taskmanagement.task.repository;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    @Query(value = "select * from task where task_id=:id",nativeQuery = true)
    Task getOneById(Long id);

//    Task getOneByIssueDate(String issueDate);

}
