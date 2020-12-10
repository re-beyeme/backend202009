package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.TaskException;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO saveTask(@RequestBody TaskDTO taskEntity){
    	return taskService.saveTaskEntity(taskEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id){
    	Optional<TaskDTO> task = taskService.getTaskEntityById(id);
    
    	if (task.isPresent()) {
    		return new ResponseEntity<>(task.get(), HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
	public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam("page") int page, @RequestParam("size") int size){

        List<TaskDTO> tasks = taskService.getAllTaskEntity(page,size);

        if(tasks.isEmpty()){      
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(tasks, HttpStatus.OK);
   }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO task){
    	try {
			return new ResponseEntity<>(taskService.modifyTaskEntity(id, task), HttpStatus.OK);
		} catch (TaskException e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
    	try {
			return new ResponseEntity<>(taskService.deleteTaskEntityById(id), HttpStatus.OK);
	    } catch (TaskException e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}
