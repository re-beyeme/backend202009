package com.example.demo.controller;

import com.example.demo.repository.TaskRepository;
import com.example.demo.service.SubTaskService;
import com.example.demo.service.TaskService;
import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import org.h2.util.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/TaskEntity")
public class TaskController implements CommandLineRunner {
    private static final int NPAGE=1;
    private static final int NUMPAGE=3;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SubTaskService subTaskService;
    @Override
    public void run(String... args) throws Exception {
        List<SubTaskEntity> subTaskEntityList=new ArrayList<>();
        boolean complet=true;
        LocalDate date=LocalDate.now();
        LocalDate date1=LocalDate.now();

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ISO_LOCAL_DATE;
        /// CREACIION DE SUBTAREAS y SUBTAREAS
        SubTaskEntity subTaskEntity=new SubTaskEntity("Soy primerSubTas",complet,TaskPriority.LOW,subTaskEntityList,date);
        subTaskEntityList.add(subTaskEntity);
        subTaskEntity.setSubTaskEntityList(subTaskEntityList);
        subTaskService.saveSubTaskEntity(subTaskEntity);// SubTask a la bbdd
        taskService.saveTaskEntity(new TaskEntity("Primera entidad de Task",complet, TaskPriority.HIGH,subTaskEntityList,date));
        // SubTask 2 y task 2
        SubTaskEntity subTaskEntity1=new SubTaskEntity("Soy segundoSubTas",complet,TaskPriority.MEDIUM,subTaskEntityList,date);
        subTaskEntityList.add(subTaskEntity1);
        subTaskEntity1.setSubTaskEntityList(subTaskEntityList);
        subTaskService.saveSubTaskEntity(subTaskEntity1);// SubTask 2 a la bbdd
        taskService.saveTaskEntity(new TaskEntity("Segunda entidad de Task",false, TaskPriority.LOW,subTaskEntityList,date));
    }
// crear una tarea
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskEntity postTaskEntity(@RequestBody TaskEntity taskEntity){
        TaskEntity task=taskService.saveTaskEntity(taskEntity);
        return  task;
    }

    // devolver una tarea por id
    @GetMapping(value = "{id}")
    public ResponseEntity<TaskEntity> getTaskEntity(@PathVariable Long id){
        TaskEntity entity=taskService.getTaskEntityById(id);
        if ((entity==null)){
            return new ResponseEntity<>(entity,HttpStatus.NOT_FOUND);
        }
        else {return new ResponseEntity<>(entity,HttpStatus.OK);
        }
    }

    // devolver una lista de tareas
   @GetMapping(value = "/")
    public ResponseEntity<List<TaskEntity>> taskEntityList(){
        List<TaskEntity>entities=taskService.getAllTaskEntity(NPAGE,NUMPAGE).getContent();
        if(entities!=null){
            return new ResponseEntity<>(entities,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(entities,HttpStatus.NOT_FOUND);
        }
   }

   // Actualizar una tarea
    @PutMapping(value = "{id}")
    public ResponseEntity<TaskEntity> putTaskEntity(@PathVariable Long id, @RequestBody TaskEntity taskEntity){
        TaskEntity entity=taskService.getTaskEntityById(id);
        if (entity!=null){
            entity.setId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Borrar una tarea
    @DeleteMapping(value = "{id}")
    public ResponseEntity<TaskEntity> deleteTaskEntity(@PathVariable Long id){
        TaskEntity entity=taskService.getTaskEntityById(id);
        if (entity!=null){
            return new ResponseEntity<>(entity,HttpStatus.OK);

        }
        else {  return new ResponseEntity<>(entity,HttpStatus.NOT_FOUND);
        }
    }

}
