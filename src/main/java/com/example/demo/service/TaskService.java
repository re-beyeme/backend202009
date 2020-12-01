package com.example.demo.service;

import com.example.demo.exception.TaskException;
import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    TaskEntity saveTaskEntity(TaskEntity taskEntity);// guardar la tarea en bbdd
    Page<TaskEntity> getAllTaskEntity(int iniPage,int finpage);// Devolver lista de todas las tareas
    TaskEntity getTaskEntityById(Long id) ;// devolver tarea por su id
    Optional<TaskEntity> getTaskByPriority(TaskPriority priority) throws TaskException;// devolver tarea por prioridad
    TaskEntity getLastTaskEntity() throws TaskException;// delvolver la una tarea de la lista
    Optional<TaskEntity>getTaskEntityByDate(LocalDate data) throws TaskException;
    TaskEntity modifyTaskEntity(Long id, TaskEntity taskEntity) throws TaskException;// actualizar una tarea por id
    TaskEntity deleteOneTaskEntityById(Long id) throws TaskException;// borrar una tarea por id en la bbdd

}
