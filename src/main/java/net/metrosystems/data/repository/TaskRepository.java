package net.metrosystems.data.repository;

import com.datastax.driver.mapping.Mapper;
import net.metrosystems.data.CassandraPersistenceService;
import net.metrosystems.data.accessors.TaskAccessor;
import net.metrosystems.data.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class TaskRepository implements Comparable<Task> {
  private final CassandraPersistenceService persistenceService;
  private final TaskAccessor accessor;

  public TaskRepository(CassandraPersistenceService persistenceService) {
    this.persistenceService = persistenceService;
    this.accessor = persistenceService.getMappingManager().createAccessor(TaskAccessor.class);
  }

  public void saveTask(Task task) {
    Mapper<Task> mapper = persistenceService.getMappingManager().mapper(Task.class);
    task.setModified(new Date());
    task.setCreated(new Date());
    task.setStatus("NOT DONE");
    task.setId(UUID.randomUUID());
    mapper.save(task);
  }

  public Task getTaskById(UUID id) {
    return accessor.getTaskById(id);
  }

  public List<Task> getAll() {
    return accessor.getAllTask().all();
  }

  public void deleteTaskById(UUID id) { accessor.deleteTaskById(id); }

  public void updateTaskStatus(UUID id, String status) {
    accessor.updateTaskStatus(id,status);
  }

  @Override
  public int compareTo(Task o) {
    Date compareCreated = o.getCreated();
    return o.getCreated().compareTo(compareCreated);
  }
}
