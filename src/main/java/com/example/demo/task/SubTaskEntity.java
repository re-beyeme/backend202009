package com.example.demo.task;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

public class SubTaskEntity extends TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SubTask_Id")
    private Long id;

    public SubTaskEntity(){
        super();
    }
    public SubTaskEntity(String description,boolean completed, TaskPriority priority,List<SubTaskEntity>subTaskEntities, LocalDate date,String tipoTarea) {
     super(description,completed,priority,subTaskEntities, date,tipoTarea);
    }

}
