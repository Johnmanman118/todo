package com.todo.services;

import com.todo.entities.Task;
import com.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Transactional
@Service
public class TaskService {


    private final TaskRepository taskRepository;
//    private final EmailService emailService;

    @Autowired
    public TaskService(TaskRepository taskRepository /*, EmailService emailService*/) {
        this.taskRepository = taskRepository;
        //this.emailService=emailService;
    }

    public List<Task> loadAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> loadTaskById(int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task;
    }

    public void save(Task task) {
        taskRepository.save(task);
//        if("completed".equalsIgnoreCase(task.getStatus())){
//            int completedTasks = taskRepository.countByStatus("completed");

//            String emailBody = String.format(  "Task '%s' has been marked as completed.\nTotal completed tasks: %d",
//                    task.getTaskName(), 0 /*completedTasks*/);
//
//            emailService.sendEmail(
//                    "johnmanman118@gmail.com", // Replace with the recipient email
//                    "Task Completed Notification",
//                    emailBody
//            );
//        }

    }

    public Optional<Task> loadTaskRandomly() {
        Optional<Task> optionalTask = taskRepository.findRandomTask();
        return optionalTask;
    }

    public Task updateTask(Task task, int taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task existingTask = optionalTask.get();

        existingTask.setAssignee(task.getAssignee());
        existingTask.setBlocker(task.getBlocker());
        existingTask.setTaskName(task.getTaskName());
        existingTask.setBudget(task.getBudget());
        existingTask.setDeadLine(task.getDeadLine());
        existingTask.setLocation(task.getLocation());
        existingTask.setStartDate(task.getStartDate());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }

    public Optional<Task>  deleteTaskById(int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        taskRepository.deleteById(taskId);
        return task;
    }

    public List<Task> loadTaskByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        return tasks;
    }

    public String deleteSomeTaskById(List<Integer> taskIds) {
        taskRepository.deleteAllByIdInBatch(taskIds);
        return "all sellected tasks have been successfully deleted.";
    }

}
