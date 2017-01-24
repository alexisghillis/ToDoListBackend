package net.metrosystems.controller;

import net.metrosystems.data.domain.Task;
import net.metrosystems.data.repository.TaskRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("task")
public class TaskController {

  private final TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping(value = "task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Task getTaskById(@PathVariable("id") String id) {
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

  @DeleteMapping(value="task/{id}")
  public void deleteTaskById(@PathVariable("id") String id) { taskRepository.deleteTaskById(id);};

}
