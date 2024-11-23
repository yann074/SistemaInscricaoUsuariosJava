package com.mycompany.gestaodeprojeto.Models;

import java.util.Date;

public class MiniCursoModel {
    private int id;
    private String nome_minicurso;
    private String descricao_minicurso;
    private Date data_inic_minicurso;
    private Date data_fim_minicurso;
    private int vagas_disp_minicurso;
    private Date data_limite_minicurso; // Alterado para LocalDateTime

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_minicurso() {
        return nome_minicurso;
    }

    public void setNome_minicurso(String nome_minicurso) {
        this.nome_minicurso = nome_minicurso;
    }

    public String getDescricao_minicurso() {
        return descricao_minicurso;
    }

    public void setDescricao_minicurso(String descricao_minicurso) {
        this.descricao_minicurso = descricao_minicurso;
    }

    public Date getData_inic_minicurso() {
        return data_inic_minicurso;
    }

    public void setData_inic_minicurso(Date data_inic_minicurso) {
        this.data_inic_minicurso = data_inic_minicurso;
    }

    public Date getData_fim_minicurso() {
        return data_fim_minicurso;
    }

    public void setData_fim_minicurso(Date data_fim_minicurso) {
        this.data_fim_minicurso = data_fim_minicurso;
    }

    public int getVagas_disp_minicurso() {
        return vagas_disp_minicurso;
    }

    public void setVagas_disp_minicurso(int vagas_disp_minicurso) {
        this.vagas_disp_minicurso = vagas_disp_minicurso;
    }

    public Date getData_limite_minicurso() {
        return data_limite_minicurso;
    }

    public void setData_limite_minicurso(Date data_limite_minicurso) {
        this.data_limite_minicurso = data_limite_minicurso;
    }
}
