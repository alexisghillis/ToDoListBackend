package net.metrosystems.data.repository;

import com.datastax.driver.mapping.Mapper;
import net.metrosystems.data.CassandraPersistenceService;
import net.metrosystems.data.accessors.TaskAccessor;
import net.metrosystems.data.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
  private final CassandraPersistenceService persistenceService;
  private final TaskAccessor accessor;

  public TaskRepository(CassandraPersistenceService persistenceService) {
    this.persistenceService = persistenceService;
    this.accessor = persistenceService.getMappingManager().createAccessor(TaskAccessor.class);
  }

  public void saveTask(Task task) {
    Mapper<Task> mapper = persistenceService.getMappingManager().mapper(Task.class);
    mapper.save(task);
  }

  public Task getTaskById(String id) {
    return accessor.getTaskById(id);
  }

  public List<Task> getAll() {
    return accessor.getAllTask().all();
  }

  public void deleteTaskById(String id) { accessor.deleteTaskById(id); }

  public void updateTaskStatus(String id, String status) {accessor.updateTaskStatus(id,status);}
}
