package net.metrosystems.data.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import net.metrosystems.data.domain.Task;

@Accessor
public interface TaskAccessor {

  @Query("select * FROM tasks WHERE id=:id")
  Task getTaskById(@Param("id") String id);

  @Query("select * FROM tasks")
  Result<Task> getAllTask();

  @Query("delete FROM tasks WHERE id=:id")
  void deleteTaskById(@Param("id") String id);

  @Query("update from tasks SET status=:status WHERE id=:id")
  void updateTaskStatus(@Param("id") String id,@Param("status") String status);
}
