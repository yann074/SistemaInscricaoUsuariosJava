/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author yanns
 */
public class PalestraModel {
    
    private int id;
    private String nomePalestra;
    private String descricaoPalestra;
    private Date dataInicioPalestra;
    private Date dataFimPalestra;
    private String vagasDisp;
    private Date dataLimite;

    public PalestraModel() {
    }

    

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePalestra() {
        return nomePalestra;
    }

    public void setNomePalestra(String nomePalestra) {
        this.nomePalestra = nomePalestra;
    }

    public String getDescricaoPalestra() {
        return descricaoPalestra;
    }

    public void setDescricaoPalestra(String descricaoPalestra) {
        this.descricaoPalestra = descricaoPalestra;
    }

    public Date getDataInicioPalestra() {
        return dataInicioPalestra;
    }

    public void setDataInicioPalestra(Date dataInicioPalestra) {
        this.dataInicioPalestra = dataInicioPalestra;
    }

    public Date getDataFimPalestra() {
        return dataFimPalestra;
    }

    public void setDataFimPalestra(Date dataFimPalestra) {
        this.dataFimPalestra = dataFimPalestra;
    }

    public String getVagasDisp() {
        return vagasDisp;
    }

    public void setVagasDisp(String vagasDisp) {
        this.vagasDisp = vagasDisp;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
}