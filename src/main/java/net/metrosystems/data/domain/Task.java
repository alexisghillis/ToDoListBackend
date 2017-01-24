package net.metrosystems.data.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Builder
public class Task {

  @Column(name = "id")
  @PartitionKey // echivaleaza aproximativ cu primary key din sql
  String id;

  @Column(name = "name")
  String name;

  @Column(name = "created")
  String created;

  @Column(name = "modified")
  String modified;

  @Column(name = "description")
  String description;

  @Column(name = "status")
  String status;
}
