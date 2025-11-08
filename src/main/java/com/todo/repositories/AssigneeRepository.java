package com.todo.repositories;

import com.todo.entities.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Integer> {
    @Query(value = "select * from assignee order by rand() limit 1" , nativeQuery = true)
    Optional<Assignee> findRandomAssignee();

}
