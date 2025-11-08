package com.todo.repositories;

import com.todo.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "select * from task order by rand() limit 1" , nativeQuery = true)
    Optional<Task> findRandomTask();
    @Query(value = "select * from task where status = :status", nativeQuery = true)
    List<Task> findByStatus(String status);
    public Page<Task> findAll(Pageable pageable);
    @Query(value = "SELECT COUNT(*) FROM task WHERE LOWER(status) = LOWER(:status)", nativeQuery = true)
    int countByStatus(String completed);
}
