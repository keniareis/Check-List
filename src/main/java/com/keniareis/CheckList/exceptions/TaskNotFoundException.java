package com.keniareis.CheckList.exceptions;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(){
        super("Task not found!");
    }
    public TaskNotFoundException(String msg){
        super(msg);
    }
}
