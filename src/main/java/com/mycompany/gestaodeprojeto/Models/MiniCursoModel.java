package com.mycompany.gestaodeprojeto.Models;

import java.sql.Time;
import java.util.Date;

public class MiniCursoModel {
    private int id;
    private String nome;
    private String descricao;
    private Date dt_minicurso;
    private Time horario_inicio_minicurso;
    private Time horario_fim_minicurso;
    private String nome_instrutor;
    private String minicurriculo_instrutor;
    private int numero_vagas;
    private Date dt_limite_inscricao; 
    private int id_evento;

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public MiniCursoModel() {
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

    public Date getDt_minicurso() {
        return dt_minicurso;
    }

    public void setDt_minicurso(Date dt_minicurso) {
        this.dt_minicurso = dt_minicurso;
    }

    public Time getHorario_inicio_minicurso() {
        return horario_inicio_minicurso;
    }

    public void setHorario_inicio_minicurso(Time horario_inicio_minicurso) {
        this.horario_inicio_minicurso = horario_inicio_minicurso;
    }

    public Time getHorario_fim_minicurso() {
        return horario_fim_minicurso;
    }

    public void setHorario_fim_minicurso(Time horario_fim_minicurso) {
        this.horario_fim_minicurso = horario_fim_minicurso;
    }

    public String getNome_instrutor() {
        return nome_instrutor;
    }

    public void setNome_instrutor(String nome_instrutor) {
        this.nome_instrutor = nome_instrutor;
    }

    public String getMinicurriculo_instrutor() {
        return minicurriculo_instrutor;
    }

    public void setMinicurriculo_instrutor(String minicurriculo_instrutor) {
        this.minicurriculo_instrutor = minicurriculo_instrutor;
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

   
}
