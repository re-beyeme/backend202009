package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.TaskException;

@Service
public interface TaskService {

    TaskDTO saveTaskEntity(TaskDTO task);// guardar la tarea en bbdd

    List<TaskDTO> getAllTaskEntity(int iniPage,int finpage);// Devolver lista de todas las tareas

    Optional<TaskDTO> getTaskEntityById(Long id) ;// devolver tarea por su id

    TaskDTO modifyTaskEntity(Long id, TaskDTO task) throws TaskException ; // actualizar una tarea por id

    TaskDTO deleteTaskEntityById(Long id) throws TaskException;// borrar una tarea por id en la bbdd

    boolean existsById(Long id);

}
