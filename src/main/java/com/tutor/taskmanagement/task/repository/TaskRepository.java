package com.tutor.taskmanagement.task.repository;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //    @Query(value = "select * from task where task_id=:id",nativeQuery = true) //SQL query
    Task getOneById(Long id);

    @Query("select t from Task t where t.taskName like %:s%")
        //JPQl -> java persistence query language
    Page<Task> findAll(Pageable pageable, @Param("s") String s);

    @Query("select t from Task t where t.userId=:id and t.taskName like %:s%")
    Page<Task> findAllByUserId(Pageable pageable, @Param("id") Long id, @Param("s") String s);
}
