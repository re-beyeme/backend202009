package com.example.demo.repository;

import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    Page<TaskEntity> findAll(Pageable pageable);
    Optional<TaskEntity> findByPriority(TaskPriority priority);
    Optional<TaskEntity> findByFechaCreacion(LocalDate date);
    boolean existsById(Long id);
}
