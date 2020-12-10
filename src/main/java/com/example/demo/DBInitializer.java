package com.example.demo;

import java.time.LocalDate;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SubTaskDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;
import com.example.demo.task.TaskPriority;

@Component
public class DBInitializer {
	
	@Autowired
	private TaskService taskService;

	@PostConstruct
	public void init() {

		/// CREACION DE TAREAS-SUBTAREAS (SUBTASKS CREATION)
		TaskDTO task1 = new TaskDTO("Task1", false, TaskPriority.LOW, LocalDate.now());
		task1.setSubTasks(Arrays.asList(
				new SubTaskDTO("subtask1-1", false, TaskPriority.HIGH, LocalDate.now()),
				new SubTaskDTO("subtask1-2", false, TaskPriority.HIGH, LocalDate.now()),
				new SubTaskDTO("subtask1-3", false, TaskPriority.HIGH, LocalDate.now())
		));
		taskService.saveTaskEntity(task1);//

		TaskDTO task2 = new TaskDTO("Task2", false, TaskPriority.MEDIUM, LocalDate.now());
		task2.setSubTasks(Arrays.asList(
				new SubTaskDTO("subtask2-1", false, TaskPriority.MEDIUM, LocalDate.now()),
				new SubTaskDTO("subtask2-2", true, TaskPriority.LOW, LocalDate.now())		
		));
		taskService.saveTaskEntity(task2);

	}

}
