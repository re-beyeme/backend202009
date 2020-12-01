package com.example.demo.task;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class SubTaskEntity extends TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SubTask_Id")
    private Long id;

    public SubTaskEntity() {
        super();
    }
    public SubTaskEntity(String descripcion, boolean completed, TaskPriority priority, List<SubTaskEntity> subTaskEntities, LocalDate date){
        super(descripcion, completed,priority,subTaskEntities,date);
    }
    public  String toString(){
        return super.toString();
    }
}
