package com.example.demo.task;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TaskEntity")
@Getter
@Setter
@ToString
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TASK_GEN")
	@Column(name = "task_entity_id")
	private Long id;

	@Column(name = "task_entity_descripcion")
	private String description;

	@Column(name = "task_entity_completed")
	private boolean completed;

	@Column(name = "task_entity_priority")
	private TaskPriority priority;

	@Column(name = "task_entity_created_date")
	private LocalDate createdDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SubTaskEntity> subTasks;

}
