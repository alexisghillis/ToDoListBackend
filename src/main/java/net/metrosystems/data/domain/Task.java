package net.metrosystems.data.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Builder
public class Task {

    @Column(name = "id")
    @PartitionKey // echivaleaza aproximativ cu primary key din sql
            UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "created")
    Date created;

    @Column(name = "modified")
    Date modified;

    @Column(name = "description")
    String description;

    @Column(name = "status")
    String status;
}
