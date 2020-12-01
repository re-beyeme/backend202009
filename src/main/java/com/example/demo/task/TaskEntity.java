package com.example.demo.task;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TaskEntity")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TaskEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TaskEntity_id")
    private Long id;
    @Column(name = "TaskEntity_Descripcion")
    private String description;
    @Column(name = "TaskEntity_Completed")
    private boolean completed;
    @Column(name = "TaskEntity_Priority")
    private TaskPriority priority;
    @Column(name = "TaskEntity_Fecha_Creacion")
    private LocalDate fechaCreacion;
    @Column(name = "TypeTask")
    private String tipoTarea;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<SubTaskEntity>subTaskEntityList=new ArrayList<>();

    public TaskEntity(){

    }
    public TaskEntity(String description,boolean completed, TaskPriority priority,List<SubTaskEntity>subTaskEntities, LocalDate date,String tipoTarea){
        this.description=description;
        this.completed=completed;
        this.priority=priority;
        this.subTaskEntityList=subTaskEntities;
        this.fechaCreacion=date;
        this.tipoTarea=tipoTarea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TaskPriority getPriority() {
        return priority;
    }
    public TaskPriority getTaskPriority(){
        return this.priority;
    }
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public  String toString(){
        return String.format("Id:" + this.id + " Descripcion:" + this.description + " Completed:" + this.completed + " Prioridad:" + this.priority +
                " Fecha de creacion:" + new SimpleDateFormat("dd/MM/yyyy").format(fechaCreacion));
    }
}
