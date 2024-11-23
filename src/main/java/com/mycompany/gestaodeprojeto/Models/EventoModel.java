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
public class EventoModel {
    private int id;
    public String nome;
    public String descricao_evento;
    public Date data_inic;
    public Date data_fim;
    public int vagas_disp;
    public Date data_limite;
    public String nome_responsavel;
    public String cpf_responsavel;
    public String email_responsavel;

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getCpf_responsavel() {
        return cpf_responsavel;
    }

    public void setCpf_responsavel(String cpf_responsavel) {
        this.cpf_responsavel = cpf_responsavel;
    }

    public String getEmail_responsavel() {
        return email_responsavel;
    }

    public void setEmail_responsavel(String email_responsavel) {
        this.email_responsavel = email_responsavel;
    }
    
    //usar id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao_evento() {
        return descricao_evento;
    }

    public void setDescricao_evento(String desc_evento) {
        this.descricao_evento = desc_evento;
    }

    public Date getData_inic() {
        return data_inic;
    }

    public void setData_inic(Date data_inic) {
        this.data_inic = data_inic;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public int getVagas_disp() {
        return vagas_disp;
    }

    public void setVagas_disp(int vagas_disp) {
        this.vagas_disp = vagas_disp;
    }

    public Date getData_limite() {
        return data_limite;
    }

    public void setData_limite(Date data_limite) {
        this.data_limite = data_limite;
    }
}
