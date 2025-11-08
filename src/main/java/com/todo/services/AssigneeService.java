package com.todo.services;

import com.todo.entities.Address;
import com.todo.entities.Assignee;
import com.todo.repositories.AddressRepository;
import com.todo.repositories.AssigneeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssigneeService {
    @Autowired
    private AssigneeRepository assigneeRepository;
    @Autowired
    AddressRepository addressRepository;

    public Optional<Assignee> loadAssigneeById(int assigneeId) {
        Optional<Assignee> assignee = assigneeRepository.findById(assigneeId);
        return assignee;
    }

    public void save(Assignee assignee) {
            assigneeRepository.save(assignee);
    }

    public Optional<Assignee> loadAssigneeRandomly(){
        Optional<Assignee> optionalAssignee = assigneeRepository.findRandomAssignee();
        return  optionalAssignee;
    }

}
