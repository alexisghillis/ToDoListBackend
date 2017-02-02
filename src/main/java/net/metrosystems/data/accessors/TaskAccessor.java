package net.metrosystems.data.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import net.metrosystems.data.domain.Task;

import java.util.UUID;

@Accessor
public interface TaskAccessor {

  @Query("select * FROM tasks WHERE id=:id")
  Task getTaskById(@Param("id") UUID id);

  @Query("select * FROM tasks")
  Result<Task> getAllTask();

  @Query("delete FROM tasks WHERE id=:id")
  void deleteTaskById(@Param("id") UUID id);

  @Query("update tasks SET status=:status, modified=toTimestamp(now()) WHERE id=:id")
  void updateTaskStatus(@Param("id") UUID id, @Param("status") String status);
}
