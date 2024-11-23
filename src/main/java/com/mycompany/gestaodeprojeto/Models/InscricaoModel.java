package com.mycompany.gestaodeprojeto.Models;

import java.util.Date;

public class InscricaoModel {
    private int id;
    private String cpf_usuario;
    private int id_evento;
    private Date data_inscricao; 

    public InscricaoModel() {
        this.data_inscricao = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public Date getData_inscricao() {
        return data_inscricao;
    }

    public void setData_inscricao(Date data_inscricao) {
        this.data_inscricao = data_inscricao;
    }
}
