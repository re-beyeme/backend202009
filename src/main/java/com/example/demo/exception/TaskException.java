package com.example.demo.exception;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskException extends Exception {
    private String mensaje;

    public TaskException(){
        super();
        this.mensaje="";
    }
    public TaskException(String mensaje){
        super();
        this.mensaje=mensaje;
    }
    @Override
    public String getMessage(){
        return this.mensaje;

    }
}
