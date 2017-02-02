package net.metrosystems.controller;

import net.metrosystems.data.domain.Task;
import net.metrosystems.data.repository.TaskRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController("task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @PutMapping(value = "task/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void putChangeStatus(@PathVariable("id") UUID id, @RequestBody String status) {
        String modifiedStatus = status.replaceAll("[^\\dA-Za-z ]", "").replaceAll("status","");
        taskRepository.updateTaskStatus(id, modifiedStatus);
    }


    @GetMapping(value = "task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Task getTaskById(@PathVariable("id") UUID id) {
        return taskRepository.getTaskById(id);
    }

    @GetMapping(value = "task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @PostMapping(value = "task", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void postNewTask(@RequestBody Task task) {
        taskRepository.saveTask(task);
    }

    @DeleteMapping(value = "task/{id}")
    public void deleteTaskById(@PathVariable("id") UUID id) {
        taskRepository.deleteTaskById(id);
    }

    ;

}
