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
    public String descricao;
    public Date dt_inicio;
    public Date dt_fim;
    public int numero_vagas;
    public Date dt_limite_inscricao;
    public String nome_responsavel;
    public String cpf_responsavel;
    public String email_responsavel;

    public EventoModel() {
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public int getNumero_vagas() {
        return numero_vagas;
    }

    public void setNumero_vagas(int numero_vagas) {
        this.numero_vagas = numero_vagas;
    }

    public Date getDt_limite_inscricao() {
        return dt_limite_inscricao;
    }

    public void setDt_limite_inscricao(Date dt_limite_inscricao) {
        this.dt_limite_inscricao = dt_limite_inscricao;
    }

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



}
