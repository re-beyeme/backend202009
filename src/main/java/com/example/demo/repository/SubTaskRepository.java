package com.example.demo.repository;

import com.example.demo.task.SubTaskEntity;
import com.example.demo.task.TaskEntity;
import com.example.demo.task.TaskPriority;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface SubTaskRepository extends JpaRepository<SubTaskEntity,Long> {
    Page<SubTaskEntity> getAll(Pageable pageable);
    Optional<SubTaskEntity> findByFechaCreacion(LocalDate date);
    Optional<SubTaskEntity> findByPriority(TaskPriority taskEntity);

}
