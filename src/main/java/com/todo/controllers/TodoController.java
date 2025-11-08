package com.todo.controllers;

import com.todo.entities.Task;
import com.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
   private TaskService taskService;
    public  TodoController (TaskService taskService){
        this.taskService = taskService;
    }
    // API's for todo app
    // 1. Get All in GET: http://localhost:8080/api/tasks
    // 2. POST: http://localhost:8080/api/tasks/create
    // 3. Get By ID: http://localhost:8080/api/tasks/id/{taskId}
    // 4. Get by Status: http://localhost:8080/api/tasks/status/{status}
    // 5. Update: http://localhost:8080/api/tasks/update/{taskId}
    // 6. Delete - Single delete:  http://localhost:8080/api/tasks/delete/{taskId}
    // 7. Delete - delete in batch: http://localhost:8080/api/tasks/delete

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE', 'ROLE_STUDENT)")
//    @GetMapping("/login")
//    public String login() {
//        return "login"; // maps to login.html in /templates
//    }
//
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE', 'ROLE_STUDENT)")
//    @GetMapping("/home")
//    public String home() {
//        return "Welcome to the Todo Task MManagement home page"; // you need home.html in /templates
//    }
    @PreAuthorize("hasAuthority('student:write')")
    @PostMapping("/tasks/create")
    public ResponseEntity<Task> createFullTaskAssignment(@RequestBody Task task) {
        taskService.save(task);
        return  ResponseEntity.ok(task);
    }

    //Admins Can Access this
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTodoTasks(){

        List<Task>  pagedTasks = taskService.loadAllTasks();
        return ResponseEntity.ok(pagedTasks);
    }
    @PreAuthorize("")
    @GetMapping("/tasks/id/{taskId}")
    public Optional<Task> getTaskById(@PathVariable("taskId") int taskId){
        Optional<Task> optionalTask = taskService.loadTaskById(taskId);
        return optionalTask;
    }
    @PreAuthorize("")
    @GetMapping("/tasks/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable("status") String status){
        List<Task> tasks = taskService.loadTaskByStatus(status);
        return tasks;
    }
    @PreAuthorize("")
    @PutMapping("/tasks/update/{taskId}")
    public Task updateTasks(@RequestBody Task task, @PathVariable("taskId") int taskId){
        return taskService.updateTask(task, taskId);
    }
    @PreAuthorize("")
    @DeleteMapping("tasks/delete/{taskId}")
    public Optional<Task>  deleteTaskById(@PathVariable("taskId") int taskId){
        return taskService.deleteTaskById(taskId);
    }

    //Students with erite authority can access this
    @PreAuthorize("hasAuthority('student:write')")
    @DeleteMapping("tasks/delete")
    public Object deleteSomeTasks(@RequestBody() List<Integer> taskIds){
        if (taskIds == null || taskIds.isEmpty()) {
            return ResponseEntity.badRequest().body("No task IDs provided.");
        }
        return taskService.deleteSomeTaskById(taskIds);
    }
}
