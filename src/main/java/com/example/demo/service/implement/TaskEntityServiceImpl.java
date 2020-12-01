package com.example.demo.service.implement;

import com.example.demo.exception.TaskException;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskEntityServiceImpl implements TaskService {
    @Autowired
    private TaskRepository  taskRepository;

    @Override
    public Page<TaskEntity> getAllTaskEntity(int nPage, int numPerpage) {
        return taskRepository.findAll(PageRequest.of(nPage,numPerpage));
    }

    @Override
    public TaskEntity getTaskEntityById(Long id) {

        return taskRepository.getOne(id);
    }

    @Override
    public Optional<TaskEntity> getTaskByPriority(TaskPriority priority) throws TaskException{
        if(! taskRepository.findByPriority(priority).isPresent()){
            throw new TaskException(" Entity not found");
        }
        return taskRepository.findByPriority(priority);
    }

    @Override
    public TaskEntity saveTaskEntity(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity modifyTaskEntity(Long id, TaskEntity taskEntity) throws TaskException {
        if(! taskRepository.existsById(id)){
            throw new  TaskException(" ENTITY_NOT EXISTS");
        }
        taskEntity.setId(id);
        return taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity getLastTaskEntity() throws TaskException{
        List<TaskEntity> taskEntities=new ArrayList<>(taskRepository.findAll());
        if (taskEntities.isEmpty()){
            throw new TaskException("ENTITY_NOT EXISTS");
        }
        return taskEntities.get(taskEntities.size()-1);
    }

    @Override
    public Optional<TaskEntity> getTaskEntityByDate(LocalDate data) throws TaskException{
        if (!taskRepository.findByFechaCreacion(data).isPresent()){
            throw new TaskException("ENTITY_NOTEXISTS");
        }
        return taskRepository.findByFechaCreacion(data);
    }

    @Override
    public TaskEntity deleteOneTaskEntityById(Long id)throws TaskException {
        if (! taskRepository.findById(id).isPresent()){
            throw new TaskException(" ENTITY_NOT EXISTS");
        }
        TaskEntity taskEntity=taskRepository.getOne(id);
       taskRepository.delete(taskEntity);
       return taskEntity;
    }
}
