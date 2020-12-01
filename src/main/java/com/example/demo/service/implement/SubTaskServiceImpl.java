package com.example.demo.service.implement;

import com.example.demo.exception.TaskException;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.service.SubTaskService;
import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SubTaskServiceImpl implements SubTaskService {
    @Autowired
    SubTaskRepository subTaskRepository;

    @Override
    public SubTaskEntity saveSubTaskEntity(SubTaskEntity subTaskEntity) {
        return subTaskRepository.save(subTaskEntity);
    }

    @Override
    public Page<SubTaskEntity> getAllSubTaskEntity(int   nPage,int numPage) {

        return subTaskRepository.getAll(PageRequest.of(nPage,numPage));
    }

    @Override
    public Optional<SubTaskEntity> getSubTaskEntityById(Long id) {
        return subTaskRepository.findById(id);
    }

    @Override
    public Optional<SubTaskEntity> getSubTaskEntityByPriority(TaskPriority priority) throws TaskException {
        if (! subTaskRepository.findByPriority(priority).isPresent()){
            throw new TaskException(" ENTITY NOT EXISTS");
        }
        return subTaskRepository.findByPriority(priority);
    }

    @Override
    public Optional<SubTaskEntity> getSubTaskEntityByFechaCreacion(LocalDate date)throws TaskException {
        if (! subTaskRepository.findByFechaCreacion(date).isPresent()){
            throw new TaskException(" ENTITY NOT FOUND");
        }
        return subTaskRepository.findByFechaCreacion(date);
    }

    @Override
    public Optional<SubTaskEntity> modifySubTaskEntity(Long id, SubTaskEntity entity) throws TaskException {
        if (! subTaskRepository.findById(id).isPresent()){
            throw new TaskException(" Entity not found");
        }
        entity.setId(id);
        return subTaskRepository.findById(id);
    }

    @Override
    public SubTaskEntity deleteSubTaskEntity(Long id){
       SubTaskEntity entity=subTaskRepository.getOne(id);
       if (entity !=null){
           subTaskRepository.delete(entity);
           return entity;
       }
       else {
           return entity;
       }
    }
}
