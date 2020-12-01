package com.example.demo.service;

import com.example.demo.exception.TaskException;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public interface SubTaskService {
    SubTaskEntity saveSubTaskEntity(SubTaskEntity subTaskEntity);
    Page<SubTaskEntity> getAllSubTaskEntity(int nPage, int numPage);
    Optional<SubTaskEntity> getSubTaskEntityById(Long id);
    Optional<SubTaskEntity> getSubTaskEntityByPriority(TaskPriority priority) throws TaskException;
    Optional<SubTaskEntity> getSubTaskEntityByFechaCreacion(LocalDate date) throws TaskException;
    Optional<SubTaskEntity> modifySubTaskEntity(Long id, SubTaskEntity entity) throws TaskException;
    SubTaskEntity deleteSubTaskEntity(Long id) ;

}
