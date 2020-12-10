package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.task.TaskPriority;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SubTaskDTO  {

	private Long id;
    private String description;
    private boolean completed;
    private TaskPriority priority;
    
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
    private LocalDate createdDate;

	public SubTaskDTO(String description, boolean completed, TaskPriority priority, LocalDate createdDate) {
		this.description = description;
		this.completed = completed;
		this.priority = priority;
		this.createdDate = createdDate;
	}
	
	
 
}
