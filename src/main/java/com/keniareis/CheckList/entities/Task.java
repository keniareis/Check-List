package com.keniareis.CheckList.entities;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String nome;
    private LocalDate date;
    private String status;

    public Task(){}

    public Task(Long id, String nome, LocalDate date, String status) {
        this.id = id;
        this.nome = nome;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
