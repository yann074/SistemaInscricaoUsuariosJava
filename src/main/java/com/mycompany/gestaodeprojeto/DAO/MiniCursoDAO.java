/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Controller.*;
import com.mycompany.gestaodeprojeto.DAO.ConnDao;
import com.mycompany.gestaodeprojeto.Models.MiniCursoModel;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MiniCursoDAO {
    //Json do senhor tbm deu errado aqui, favor testar com o json deixado no final desse codigo
    
    
public boolean salvarMiniCurso(MiniCursoModel miniCurso) {
    String sql = "INSERT INTO mini_curso (nome, descricao, dt_minicurso, horario_inicio_minicurso, horario_fim_minicurso, "
               + "nome_instrutor, minicurriculo_instrutor, numero_vagas, dt_limite_inscricao, id_evento) VALUES (?, ?,?,?,?,?,?,?,?,?)";
    
    Connection conn = null;
    PreparedStatement stm = null;

    try {
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);

        // Configurar os parâmetros da consulta
        stm.setString(1, miniCurso.getNome());
        stm.setString(2, miniCurso.getDescricao());

        // Converter e configurar as datas e horários
        java.sql.Date dtMinicurso = new java.sql.Date(miniCurso.getDt_minicurso().getTime());
        stm.setDate(3, dtMinicurso);

        java.sql.Time horarioInicio = new java.sql.Time(miniCurso.getHorario_inicio_minicurso().getTime());
        stm.setTime(4, horarioInicio);

        java.sql.Time horarioFim = new java.sql.Time(miniCurso.getHorario_fim_minicurso().getTime());
        stm.setTime(5, horarioFim);

        // Configurar os demais campos
        stm.setString(6, miniCurso.getNome_instrutor());
        stm.setString(7, miniCurso.getMinicurriculo_instrutor());
        stm.setInt(8, miniCurso.getNumero_vagas());

        java.sql.Date dataLimiteInscricao = new java.sql.Date(miniCurso.getDt_limite_inscricao().getTime());
        stm.setDate(9, dataLimiteInscricao);
        stm.setInt(10, miniCurso.getId_evento());

        // Executar a consulta
        int resultado = stm.executeUpdate();

        if (resultado == 1) {
            System.out.println("MiniCurso salvo com sucesso!");
            return true;
        }

    } catch (SQLException e) {
        System.out.println("Erro ao salvar MiniCurso: " + e.getMessage());
        return false;
    } finally {
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexões: " + e.getMessage());
        }
    }

    return false;
}

    
    public List<MiniCursoModel> listarMiniCurso(){
        String sql = "SELECT * FROM mini_curso";
        ArrayList<MiniCursoModel> minicursos = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                MiniCursoModel minicurso = new MiniCursoModel();
                
              minicurso.setNome(rs.getString("nome"));
            minicurso.setDescricao(rs.getString("descricao"));
            minicurso.setDt_minicurso(rs.getTimestamp("dt_minicurso"));
            minicurso.setHorario_inicio_minicurso(rs.getTime("horario_inicio_minicurso"));
            minicurso.setHorario_fim_minicurso(rs.getTime("horario_fim_minicurso"));
            minicurso.setNumero_vagas(rs.getInt("numero_vagas"));

                
                minicursos.add(minicurso);
                
            }
        }catch(SQLException e){
            System.out.println("Deu errrado: " + e);
        }
        return minicursos;
        
    }
    
    
    //esse tbm, triste
   public boolean removerMiniCurso(int id, Timestamp data_alt) {
    String sqlSelect = "SELECT mc.id, e.dt_inicio FROM mini_curso mc " +
                       "JOIN evento e ON mc.id_evento = e.id " +
                       "WHERE mc.id = ?";
    String sqlDelete = "DELETE FROM mini_curso WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar a data de início do evento associado ao mini curso
        stm = conn.prepareStatement(sqlSelect);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            // Obter a data de início do evento
            Timestamp dataInicio = rs.getTimestamp("dt_inicio");

            // Verificar se a data de alteração é anterior à data de início do evento
            if (data_alt.before(dataInicio)) {
                // Excluir mini curso
                stm = conn.prepareStatement(sqlDelete);
                stm.setInt(1, id);
                int result = stm.executeUpdate();

                if (result == 1) {
                    System.out.println("Mini curso deletado com sucesso");
                    return true;
                } else {
                    System.out.println("Nenhuma linha foi deletada");
                }
            } else {
                System.out.println("Data de alteração não é anterior à data inicial do evento.");
            }
        } else {
            System.out.println("Mini curso não encontrado");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao remover mini curso: " + e.getMessage());
    } 
    return false;
}

    //dnv eu tive ajuda, infelizmente
   public boolean atualizarMiniCurso(MiniCursoModel miniCurso, int id, Date data_alt) {
    String sqlSelect = "SELECT e.data_inicio FROM mini_curso mc " +
                       "JOIN evento e ON mc.id_evento = e.id " +
                       "WHERE mc.id = ?";
    
    String sqlUpdate = "UPDATE mini_curso SET nome_minicurso = ?, descricao_minicurso = ?, " +
                       "data_inic_minicurso = ?, data_fim_minicurso = ?, vagas_disp_minicurso = ?, " +
                       "data_limite_minicurso = ?, nome_instrutor = ?, minicurriculo_instrutor = ?, " +
                       "numero_vagas = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar a data de início do evento associado ao mini curso
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
                stm.setString(1, miniCurso.getNome());
                stm.setString(2, miniCurso.getDescricao());

                // Converter e configurar as datas e horários
                java.sql.Date dtMinicurso = new java.sql.Date(miniCurso.getDt_minicurso().getTime());
                stm.setDate(3, dtMinicurso);

                java.sql.Time horarioInicio = new java.sql.Time(miniCurso.getHorario_inicio_minicurso().getTime());
                stm.setTime(4, horarioInicio);

                java.sql.Time horarioFim = new java.sql.Time(miniCurso.getHorario_fim_minicurso().getTime());
                stm.setTime(5, horarioFim);

                // Configurar os demais campos
                stm.setString(6, miniCurso.getNome_instrutor());
                stm.setString(7, miniCurso.getMinicurriculo_instrutor());
                stm.setInt(8, miniCurso.getNumero_vagas());

                java.sql.Date dataLimiteInscricao = new java.sql.Date(miniCurso.getDt_limite_inscricao().getTime());
                stm.setDate(9, dataLimiteInscricao);

                // Configurar o ID para a atualização
                stm.setInt(10, id);

                // Executar a atualização
                int resultado = stm.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Atualizado com sucesso");
                    return true;
                } else {
                    System.out.println("Algo deu errado, nenhuma linha foi atualizada");
                }
            } else {
                System.out.println("Não é possível atualizar o mini curso. O evento já começou.");
            }
        } else {
            System.out.println("Mini curso não encontrado");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar mini curso: " + e.getMessage());
    }
    return false;
}

    /*{
  "nome": "Introdução ao Java",
  "descricao": "MiniCurso sobre os fundamentos da linguagem c.",
  "dt_minicurso": "2024-11-25",
   "horario_inicio_minicurso": "10:30:00",
  "horario_fim_minicurso": "10:30:00",
  "nome_instrutor": "Carlos Silva",
  "minicurriculo_instrutor": "Carlos é instrutor de Java com mais de 10 anos de experiência no mercado.",
  "numero_vagas": 30,
  "dt_limite_inscricao": "2024-11-20"
}
*/
    
}
