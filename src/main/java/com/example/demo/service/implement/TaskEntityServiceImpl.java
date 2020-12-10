package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SubTaskDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.TaskException;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskEntityServiceImpl implements TaskService {

    @Autowired
    private TaskRepository  taskRepository;

    @Override
    public Optional<TaskDTO> getTaskEntityById(Long id) {
        return taskRepository.findById(id).map(this::toDTO);
    }

    @Override
    public TaskDTO saveTaskEntity(TaskDTO task){
        return toDTO(taskRepository.save(toEntity(task)));
    }

    @Override
    public List<TaskDTO> getAllTaskEntity(int iniPage, int finpage) {
        return taskRepository.findAll(PageRequest.of(iniPage, finpage, Sort.by(Order.asc("createdDate"))))
        		.map(this::toDTO).toList();
    }


    @Override
    public TaskDTO modifyTaskEntity(Long id, TaskDTO task) throws TaskException {
        if(taskRepository.existsById(id)){
        	task.setId(id);
            TaskEntity taskModified = taskRepository.save(toEntity(task));
            log.info("Se ha modificado correctamente el task {}", id);
            return toDTO(taskModified);
        } else {
            throw new TaskException("Entity not found");
        }
    }

    @Override
    public TaskDTO deleteTaskEntityById(Long id) throws TaskException{

        TaskEntity entity= taskRepository.getOne(id);

        if (entity != null){
            taskRepository.delete(entity);
            log.info("Se ha borrado correctamente el task {}", id);
            return toDTO(entity);
        } else {
            throw new TaskException("Task not found");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }
    
    private TaskDTO toDTO(TaskEntity entity) {
    	TaskDTO dto = new TaskDTO();
    	BeanUtils.copyProperties(entity, dto);
    	if (entity.getSubTasks() != null) {
    		dto.setSubTasks(subTaskToDTO(entity.getSubTasks()));
    	}
    	return dto;
    }
    
    private TaskEntity toEntity(TaskDTO dto) {
    	TaskEntity entity = new TaskEntity();
    	BeanUtils.copyProperties(dto, entity);
    	if (dto.getSubTasks() != null) {
    		entity.setSubTasks(subTaskToEntity(dto.getSubTasks()));
    	}
    	return entity;
    }
    
    private List<SubTaskEntity> subTaskToEntity(List<SubTaskDTO> dtos) {
    	return dtos.stream().map(dto -> {
    		SubTaskEntity entity = new SubTaskEntity();
        	BeanUtils.copyProperties(dto, entity);
        	return entity;
    	}).collect(Collectors.toList());
    	
    }
    private List<SubTaskDTO> subTaskToDTO(List<SubTaskEntity> entities) {
    	return entities.stream().map(entity -> {
    		SubTaskDTO dto = new SubTaskDTO();
        	BeanUtils.copyProperties(entity, dto);
        	return dto;
    	}).collect(Collectors.toList());
    	
    }
}
