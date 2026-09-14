package br.com.uniube.todo.model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String titulo;
    private boolean concluida;
    private String descricao;
    private LocalDate datacriacao;

    public Tarefa(){

    }
    

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.datacriacao = LocalDate.now();
    }
 
    // Construtor completo
    public Tarefa(int id, String titulo, String descricao, boolean concluida, LocalDate dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.datacriacao = dataCriacao;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public String getDescricao() {
        return descricao;
    }
 
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 
    public boolean isConcluida() {
        return concluida;
    }
 
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
 
    public LocalDate getDataCriacao() {
        return datacriacao;
    }
 
    public void setDataCriacao(LocalDate dataCriacao) {
        this.datacriacao = dataCriacao;
    }
 
    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", concluida=" + concluida +
                '}';
    }



}
