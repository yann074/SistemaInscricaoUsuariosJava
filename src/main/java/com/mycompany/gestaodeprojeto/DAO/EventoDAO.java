/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Controller.*;
import com.mycompany.gestaodeprojeto.DAO.ConnDao;
import com.mycompany.gestaodeprojeto.Models.EventoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yanns
 */
public class EventoDAO {
     //Um postamn de Teste no final desse codigo
    
    public boolean salvarEvento(EventoModel evento){
        String sql = "INSERT INTO evento (nome, descricao, dt_inicio, dt_fim, numero_vagas, dt_limite_inscricao, "
                + "nome_responsavel, cpf_responsavel, email_responsavel) VALUES (?,?,?,?,?,?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, evento.getNome());
            stm.setString(2, evento.getDescricao());
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(evento.getDt_inicio().getTime());
            java.sql.Date data_fim = new java.sql.Date(evento.getDt_fim().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
                        
            stm.setInt(5, evento.getNumero_vagas());
            
            //Para tratar com
            java.sql.Date data_limite = new java.sql.Date(evento.getDt_limite_inscricao().getTime());
            
            stm.setDate(6, data_limite);
            
            stm.setString(7, evento.getNome_responsavel());
            stm.setString(8, evento.getCpf_responsavel());
            stm.setString(9, evento.getEmail_responsavel());
            
            System.out.println("CPF a ser salvo: " + evento.getCpf_responsavel());
            
            int resultado = stm.executeUpdate();
            
            if(resultado == 1){
                System.out.println("Deu bom");
            }
            
            return true;
        }catch(SQLException e){
            System.out.println("error " + e.getMessage());
        }
        
        return false;
    }
    
    
    public List<EventoModel> listarEvento(String dataInicio, String dataFim){
    String sql = "SELECT * FROM evento WHERE dt_inicio >= ? AND dt_fim <= ?";
    List<EventoModel> eventos = new ArrayList<>();
    
    Connection conn = null;
    PreparedStatement stm = null;
    
    try{
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);
        
        // Definir os parâmetros de data para a consulta
        stm.setString(1, dataInicio); // Data de início
        stm.setString(2, dataFim);     // Data de fim
        
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            EventoModel evento = new EventoModel();
            
            evento.setNome(rs.getString("nome"));
            evento.setDescricao(rs.getString("descricao"));
            evento.setDt_inicio(rs.getTimestamp("dt_inicio"));
            evento.setDt_fim(rs.getTimestamp("dt_fim"));
            evento.setNumero_vagas(rs.getInt("numero_vagas"));
            evento.setDt_limite_inscricao(rs.getTimestamp("dt_limite_inscricao"));
            evento.setNome_responsavel(rs.getString("nome_responsavel"));
            evento.setCpf_responsavel(rs.getString("cpf_responsavel"));
            evento.setEmail_responsavel(rs.getString("email_responsavel"));
            
            eventos.add(evento);
        }
    }catch(SQLException e){
        System.out.println("Erro: " + e);        
    }
    return eventos;
}
    
    
   public List<EventoModel> listarEventoComPalestraEMiniCurso(int id_evento) {
    String sql = "SELECT \n" +
"    'mini_curso' AS tipo,\n" +
"    mc.id AS id,\n" +
"    mc.nome AS nome,\n" +
"    mc.descricao AS descricao,\n" +
"    mc.dt_minicurso AS dt_minicurso,\n" +
"    mc.nome_instrutor AS nome_instrutor,\n" +
"    e.nome AS nome_evento\n" +
"FROM \n" +
"    mini_curso mc\n" +
"INNER JOIN \n" +
"    evento e ON mc.id_evento = e.id\n" +
"WHERE \n" +
"    e.id = ?\n" +
"UNION ALL\n" +
"SELECT \n" +
"    'palestra' AS tipo,\n" +
"    p.id AS id,\n" +
"    p.nome AS nome,\n" +
"    p.descricao AS descricao,\n" +
"    p.dt_palestra AS dt_palestra,\n" +
"    p.data_limite AS data_limite,\n" +
"    e.nome AS nome_evento\n" +
"FROM \n" +
"    palestra p\n" +
"INNER JOIN \n" +
"    evento e ON p.id_evento = e.id\n" +
"WHERE \n" +
"    e.id = ?;"; // Aqui também usa um único "?" para o parâmetro

    List<EventoModel> eventos = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stm = null;

    try {
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);
        
        stm.setInt(1, id_evento); // Primeiro parâmetro (id_evento) para a consulta da mini_curso
        stm.setInt(2, id_evento); // Segundo parâmetro (id_evento) para a consulta da palestra
        
        ResultSet rs = stm.executeQuery();
        
        while (rs.next()) {
            EventoModel evento = new EventoModel();
    
    evento.setNome(rs.getString("nome"));
    evento.setDescricao(rs.getString("descricao"));
    evento.setDt_inicio(rs.getDate("dt_minicurso")); 
    evento.setDt_limite_inscricao(rs.getTimestamp("dt_minicurso"));
    evento.setNome_responsavel(rs.getString("nome_instrutor")); 

            eventos.add(evento);
        }
    } catch (SQLException e) {
        System.out.println("Erro: " + e);
    } finally {
        // Fechar os recursos
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar os recursos: " + e);
        }
    }
    return eventos;
}


  public boolean removerEvento(int id) {
    String sql = "DELETE FROM evento WHERE id = ? AND dt_inicio > NOW()";

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        conn = ConnDao.conn();  

        stm = conn.prepareStatement(sql);
        stm.setInt(1, id);

        int result = stm.executeUpdate();

        if (result == 1) {
            System.out.println("Deletado com sucesso");
            return true;
        } else {
            System.out.println("Nenhuma linha foi deletada. O evento pode já ter começado.");
        }

    } catch (SQLException e) {
        System.out.println("Erro: " + e);
    }
    return false;
}

    //TESTAR
   public boolean atualizarEvento(EventoModel evento, int id) {
    String sql = "UPDATE evento SET nome = ?, descricao = ?, dt_inicio = ?, "
               + "dt_fim = ?, numero_vagas = ?, dt_limite_inscricao = ?, nome_responsavel = ?, "
               + "cpf_responsavel = ?, email_responsavel = ? WHERE id = ?  AND dt_inicio > NOW()";

    Connection conn = null;
    PreparedStatement stm = null;

   
    try {
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);

       stm.setString(1, evento.getNome());
            stm.setString(2, evento.getDescricao());
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(evento.getDt_inicio().getTime());
            java.sql.Date data_fim = new java.sql.Date(evento.getDt_fim().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
                        
            stm.setInt(5, evento.getNumero_vagas());
            
            //Para tratar com
            java.sql.Date data_limite = new java.sql.Date(evento.getDt_limite_inscricao().getTime());
            
            stm.setDate(6, data_limite);

        stm.setString(7, evento.getNome_responsavel());
        stm.setString(8, evento.getCpf_responsavel());
        stm.setString(9, evento.getEmail_responsavel());

        stm.setInt(10, id);

            int result = stm.executeUpdate();

            if (result == 1) {
                System.out.println("Valores alterados com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar os valores.");
            }
        
    } catch (SQLException e) {
        System.out.println("Ocorreu um erro: " + e.getMessage());
    } finally {
        // Fechar recursos
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    return false;
}

    
        public List<EventoModel> listarProgramacaoEvento(int id){
        String sql = "SELECT * FROM evento WHERE id = ?";
        List<EventoModel> eventos = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1, id);
           
            ResultSet rs = stm.executeQuery();
            
                        
            while(rs.next()){
                EventoModel evento = new EventoModel();
                
                evento.setNome(rs.getString("nome"));
            evento.setDescricao(rs.getString("descricao"));
            evento.setDt_inicio(rs.getTimestamp("dt_inicio"));
            evento.setDt_fim(rs.getTimestamp("dt_fim"));
            evento.setNumero_vagas(rs.getInt("numero_vagas"));
            evento.setDt_limite_inscricao(rs.getTimestamp("dt_limite_inscricao"));
            evento.setNome_responsavel(rs.getString("nome_responsavel"));
            evento.setCpf_responsavel(rs.getString("cpf_responsavel"));
            evento.setEmail_responsavel(rs.getString("email_responsavel"));
            
                eventos.add(evento);
            }
        }catch(SQLException e){
            System.out.println("error " + e);        
    }          
    return eventos;
    }
    /*{
    "nome": "Nome do Evento",
    "descricao": "Descrição do evento",
    "dt_inicio": "2024-11-25T00:00:00",
    "dt_fim": "2024-11-30T00:00:00",
    "numero_vagas": 100,
    "dt_limite_inscricao": "2024-11-20T00:00:00",
    "nome_responsavel": "Nome do Responsável",
    "cpf_responsavel": "123.456.789-00",
    "email_responsavel": "responsavel@dominio.com"
  }

*/
}

