package com.example.demo.task;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SubTaskEntity")
@Getter
@Setter
@ToString
public class SubTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SUBTASK_GEN")
	@Column(name = "subtask_entity_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "task_entity_id")
	private TaskEntity task;

	@Column(name = "subtask_entity_descripcion")
	private String description;

	@Column(name = "subtask_entity_completed")
	private boolean completed;

	@Column(name = "subtask_entity_priority")
	private TaskPriority priority;

	@Column(name = "subtask_entity_created_date")
	private LocalDate createdDate;

}
