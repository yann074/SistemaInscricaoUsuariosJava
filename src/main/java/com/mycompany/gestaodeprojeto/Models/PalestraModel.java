/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Models;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author yanns
 */
public class PalestraModel {
    
    private int id;
    private String nome;
    private String descricao;
    private Date dt_palestra;
    private Time horario_fim_palestra;
    private String nome_palestrante;
    private String minicurriculo_palestrante;
    private Time horario_inicio_palestra;
    private Date data_limite;
    private int id_evento;

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public PalestraModel() {
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

    public Date getDt_palestra() {
        return dt_palestra;
    }

    public void setDt_palestra(Date dt_palestra) {
        this.dt_palestra = dt_palestra;
    }

    public Time getHorario_fim_palestra() {
        return horario_fim_palestra;
    }

    public void setHorario_fim_palestra(Time horario_fim_palestra) {
        this.horario_fim_palestra = horario_fim_palestra;
    }

    public Time getHorario_inicio_palestra() {
        return horario_inicio_palestra;
    }

    public void setHorario_inicio_palestra(Time horario_inicio_palestra) {
        this.horario_inicio_palestra = horario_inicio_palestra;
    }

    public String getNome_palestrante() {
        return nome_palestrante;
    }

    public void setNome_palestrante(String nome_palestrante) {
        this.nome_palestrante = nome_palestrante;
    }

    public String getMinicurriculo_palestrante() {
        return minicurriculo_palestrante;
    }

    public void setMinicurriculo_palestrante(String minicurriculo_palestrante) {
        this.minicurriculo_palestrante = minicurriculo_palestrante;
    }

    public Date getData_limite() {
        return data_limite;
    }

    public void setData_limite(Date data_limite) {
        this.data_limite = data_limite;
    }

    
}