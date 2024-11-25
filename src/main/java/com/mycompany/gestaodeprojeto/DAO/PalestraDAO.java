/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Models.PalestraModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yanns
 */
public class PalestraDAO {
    //Com o Json do Senhor deu erro com data, vou deixar um json no final desse codigo
    
   public boolean salvarPalestra(PalestraModel palestra) {
    String sql = "INSERT INTO palestra (nome, descricao, dt_palestra, horario_inicio_palestra, horario_fim_palestra, " +
                 "nome_palestrante, minicurriculo_palestrante, data_limite,  id_evento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Connection conn = null;
    PreparedStatement stm = null;

    try {
        conn = ConnDao.conn(); // Obtem a conexão
        stm = conn.prepareStatement(sql);

        // Configurando os parâmetros
        stm.setString(1, palestra.getNome()); // Nome da palestra
        stm.setString(2, palestra.getDescricao()); // Descrição da palestra

        // Para `dt_palestra` (Date)
        java.sql.Date dtPalestra = new java.sql.Date(palestra.getDt_palestra().getTime());
        stm.setDate(3, dtPalestra);

        // Para horários de início e fim (Time)
        stm.setTime(4, palestra.getHorario_inicio_palestra());
        stm.setTime(5, palestra.getHorario_fim_palestra());

        // Nome do palestrante e minicurrículo
        stm.setString(6, palestra.getNome_palestrante());
        stm.setString(7, palestra.getMinicurriculo_palestrante());

        // Data limite (Date)
        java.sql.Date dataLimite = new java.sql.Date(palestra.getData_limite().getTime());
        stm.setDate(8, dataLimite);
        stm.setInt(9, palestra.getId_evento());

        // Executando o comando
        int resultado = stm.executeUpdate();

        if (resultado == 1) {
            System.out.println("Palestra salva com sucesso.");
            return true;
        }

    } catch (SQLException e) {
        System.err.println("Erro ao salvar palestra: " + e.getMessage());
    } finally {
        // Fechando recursos
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    return false;
}

    
    public List<PalestraModel> listarPalestra(){
        String sql = "SELECT * FROM palestra";
        List<PalestraModel> palestras = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
           
            ResultSet rs = stm.executeQuery();
            
                        
            while(rs.next()){
                PalestraModel palestra = new PalestraModel();
                
                palestra.setNome(rs.getString("nome"));
                palestra.setDescricao(rs.getString("descricao"));
                palestra.setDt_palestra(rs.getTimestamp("dt_palestra"));
                palestra.setHorario_inicio_palestra(rs.getTime("horario_inicio_palestra"));
                palestra.setHorario_fim_palestra(rs.getTime("horario_fim_palestra"));
                
                palestras.add(palestra);
            }
        }catch(SQLException e){
            System.out.println("error " + e);        
    }          
    return palestras;
    }
    
    
    //ajuda eu tive
    public boolean removerPalestra(int id) {
    String sqlSelect = "SELECT p.id, e.dt_inicio FROM palestra p " +
                       "JOIN evento e ON p.id_evento = e.id " +
                       "WHERE p.id = ?";
    String sqlDelete = "DELETE FROM palestra WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar se a palestra existe e obter a data de início do evento
        stm = conn.prepareStatement(sqlSelect);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            // Obter a data de início do evento
            Date dataInicio = rs.getDate("dt_inicio");
            Date dataAtual = new Date(System.currentTimeMillis());

            // Verificar se o evento já começou
            if (dataInicio.before(dataAtual)) {
                System.out.println("Não é possível remover a palestra. O evento já começou.");
                return false;
            }

            // Excluir palestra
            stm = conn.prepareStatement(sqlDelete);
            stm.setInt(1, id);
            int result = stm.executeUpdate();

            if (result == 1) {
                System.out.println("Palestra deletada com sucesso");
                return true;
            } else {
                System.out.println("Nenhuma linha foi deletada");
            }
        } else {
            System.out.println("Palestra não encontrada");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao remover palestra: " + e.getMessage());
    } 
    return false;
}


   public boolean atualizarPalestra(PalestraModel palestra, int id, Date data_alt) {
    // SQL para verificar a data de início do evento associado à palestra
    String sqlSelect = "SELECT e.dt_inicio FROM palestra p " +
                       "JOIN evento e ON p.id_evento = e.id " +
                       "WHERE p.id = ?";

    // SQL de atualização da palestra
    String sqlUpdate = "UPDATE palestra SET nome = ?, descricao = ?, dt_palestra = ?, " +
                       "horario_fim_palestra = ?, nome_palestrante = ?, minicurriculo_palestrante = ?, " +
                       "horario_inicio_palestra = ?, data_limite = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar a data de início do evento associado à palestra
        stm = conn.prepareStatement(sqlSelect);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            // Obter a data de início do evento
            Date dataInicioEvento = rs.getTimestamp("dt_inicio");

            // Verificar se a data de alteração (data_alt) é anterior à data de início do evento
            if (data_alt.before(dataInicioEvento)) {
                // Preparar a consulta de atualização
                stm = conn.prepareStatement(sqlUpdate);

                // Configurar os parâmetros da consulta de atualização
                stm.setString(1, palestra.getNome()); // Nome da palestra
                stm.setString(2, palestra.getDescricao()); // Descrição da palestra

                // Para `dt_palestra` (Date)
                java.sql.Date dtPalestra = new java.sql.Date(palestra.getDt_palestra().getTime());
                stm.setDate(3, dtPalestra);

                // Para horários de início e fim (Time)
                stm.setTime(4, palestra.getHorario_inicio_palestra());
                stm.setTime(5, palestra.getHorario_fim_palestra());

                // Nome do palestrante e minicurrículo
                stm.setString(6, palestra.getNome_palestrante());
                stm.setString(7, palestra.getMinicurriculo_palestrante());

                // Data limite (Date)
                java.sql.Date dataLimite = new java.sql.Date(palestra.getData_limite().getTime());
                stm.setDate(8, dataLimite);

                // Configurar o ID para a atualização
                stm.setInt(9, id);

                // Executar a atualização
                int resultado = stm.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Valores alterados com sucesso");
                    return true;
                } else {
                    System.out.println("Algo deu errado, nenhuma linha foi atualizada");
                }
            } else {
                System.out.println("Não é possível atualizar a palestra. O evento já começou.");
            }
        } else {
            System.out.println("Palestra não encontrada");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar palestra: " + e.getMessage());
    }
    return false;
}

    /*{
  "nome": "Palestra sobre Inteligência Artificial",
  "descricao": "Uma introdução ao mundo da Inteligência Artificial e suas aplicações no cotidiano.",
  "dt_palestra": "2024-12-15", 
  "horario_inicio_palestra": "09:00:00",
  "horario_fim_palestra": "12:00:00",
  "nome_palestrante": "Dr. João Silva",
  "minicurriculo_palestrante": "Dr. João Silva é PhD em Inteligência Artificial pela Universidade X e atua no desenvolvimento de soluções de IA há mais de 10 anos.",
  "data_limite": "2024-12-10",
    "id_evento": "9"
}

    */
    
}
