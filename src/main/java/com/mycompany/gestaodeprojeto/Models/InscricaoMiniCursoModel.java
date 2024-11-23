/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Models;

import java.util.Date;

/**
 *
 * @author yanns
 */
public class InscricaoMiniCursoModel {
    
    private int id;
    private String cpf_usuario;
    private int id_mini_curso;
    private Date data_inscricao; 
    private int id_evento;

    public InscricaoMiniCursoModel() {
    }

    public InscricaoMiniCursoModel(Date data_inscricao) {
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

    public int getId_mini_curso() {
        return id_mini_curso;
    }

    public void setId_mini_curso(int id_mini_curso) {
        this.id_mini_curso = id_mini_curso;
    }

    public Date getData_inscricao() {
        return data_inscricao;
    }

    public void setData_inscricao(Date data_inscricao) {
        this.data_inscricao = data_inscricao;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
    
    
}
