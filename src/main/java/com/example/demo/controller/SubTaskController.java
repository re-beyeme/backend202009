package com.example.demo.controller;

import com.example.demo.service.SubTaskService;
import com.example.demo.service.TaskService;
import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Subtasntity")
public class SubTaskController  {
    private static final int NPAGE=1;
    private static final int NUMPAGE=3;
    @Autowired
    private SubTaskService subTaskService;

    @PostConstruct
    public void init(){
        boolean complet=true;
        List<SubTaskEntity>subTaskEntities=new ArrayList<>();
        SubTaskEntity subTask1=new SubTaskEntity( "Primer subTas",complet, TaskPriority.LOW,subTaskEntities, LocalDate.now(),"Subtarea");
        subTaskEntities.add(subTask1);
        subTask1.setSubTaskEntityList(subTaskEntities);
        subTaskService.saveSubTaskEntity(subTask1);
        SubTaskEntity subTask2=new SubTaskEntity(" Segundo subTas",complet, TaskPriority.MEDIUM,subTaskEntities, LocalDate.now(),"SubTarea");
        subTaskService.saveSubTaskEntity(subTask2);
    }
    // crear una sub tarea
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public SubTaskEntity postSubTaskEntity(@RequestBody SubTaskEntity subTaskEntity){
        SubTaskEntity entity=subTaskService.saveSubTaskEntity(subTaskEntity);
        return  entity;
    }

    // devolver una sub Tarea por id
    @GetMapping(value = "{id}")
    public ResponseEntity<SubTaskEntity> getSubTaskEntity(@PathVariable Long id){
        SubTaskEntity subTask=subTaskService.getSubTaskEntityById(id).get();
        if(subTask!=null){
            return new ResponseEntity<>(subTask,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(subTask,HttpStatus.NOT_FOUND);
        }
    }

    // devolver una lista de sub tareas
    @GetMapping(value = "/")
    public ResponseEntity<List<SubTaskEntity>> subTaskEntities(){
        List<SubTaskEntity>subTaskEntities=subTaskService.getAllSubTaskEntity(NPAGE,NUMPAGE).getContent();
        if(subTaskEntities!=null){
            return new ResponseEntity<>(subTaskEntities,HttpStatus.OK);
        }
        else { return new ResponseEntity<>(subTaskEntities,HttpStatus.NOT_FOUND);}
    }

    // Actualizar una sub tarea
    @PutMapping(value = "{id}")
    public ResponseEntity<SubTaskEntity> putSubTaskEntity(@PathVariable Long id, @RequestBody SubTaskEntity subTaskEntity){
        SubTaskEntity entity=subTaskService.getSubTaskEntityById(id).orElseThrow();
        if(entity!=null){
            entity.setId(id);
            subTaskService.saveSubTaskEntity(entity);
            return new ResponseEntity<>(entity,HttpStatus.OK);
        }
        else { return new ResponseEntity<>(entity,HttpStatus.NOT_FOUND);
        }
    }

    // Borrar una subtarea
    @DeleteMapping(value = "{id}")
    public ResponseEntity<SubTaskEntity> deleteSubTaskEntity(@PathVariable Long id){
        SubTaskEntity subTaskEntity=subTaskService.getSubTaskEntityById(id).get();
        if(subTaskEntity!=null){
            subTaskService.deleteSubTaskEntity(id);
            return new ResponseEntity<>(subTaskEntity,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(subTaskEntity,HttpStatus.NOT_FOUND);
        }
    }

}
