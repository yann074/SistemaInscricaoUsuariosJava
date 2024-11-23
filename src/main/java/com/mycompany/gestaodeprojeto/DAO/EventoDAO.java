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
     //nome do responsável pelo evento, cpf do responsável pelo evento, e-mail do responsável pelo evento
    public boolean salvarEvento(EventoModel evento){
        String sql = "INSERT INTO evento (nome, descricao_evento, data_inic, data_fim, vagas_disp, data_limite, "
                + "nome_responsavel, cpf_responsavel, email_responsavel) VALUES (?,?,?,?,?,?, ?, ?, ?)";
        
       /* {
  "nome": "Evento de Tecnologia",
  "descricao_evento": "Evento sobre inovação em TI",
  "data_inic": "2024-12-01T09:00:00",
  "data_fim": "2024-12-01T18:00:00",
  "vagas_disp": 100,
  "data_limite": "2024-11-25T23:59:59",
  "nome_responsavel": "João Silva",
  "cpf_responsavel": "123.456.789-00",
  "email_responsavel": "joao.silva@email.com"
}
        
{
  "nome": "FINC",
  "dt_inicio": "21/10/2024",
  "dt_fim": "24/10/2024",
  "descricao": "Evento de educação do Grupo Nobre",
  "nome_responsavel": "fulano",
  "cpf_responsavel": "CPF de Fulano",
  "email_responsavel": "fulano@gmail.com",
  "numero_vagas": "1000",
  "dt_limite_inscricao": "18/10/2024"
}
*/

        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, evento.getNome());
            stm.setString(2, evento.getDescricao_evento());  
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(evento.getData_inic().getTime());
            java.sql.Date data_fim = new java.sql.Date(evento.getData_fim().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
                        
            stm.setInt(5, evento.getVagas_disp());
            
            //Para tratar com
            java.sql.Date data_limite = new java.sql.Date(evento.getData_limite().getTime());
            
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
    String sql = "SELECT * FROM evento WHERE data_inic >= ? AND data_fim <= ?";
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
            evento.setDescricao_evento(rs.getString("descricao_evento"));
            evento.setData_inic(rs.getTimestamp("data_inic"));
            evento.setData_fim(rs.getTimestamp("data_fim"));
            evento.setVagas_disp(rs.getInt("vagas_disp"));
            evento.setData_limite(rs.getTimestamp("data_limite"));
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
    
    
    //ARRUMAAAAAAAAAAAAAAAAAA
    
    public List<EventoModel> listarEventoComPalestraEMiniCurso(String dataInicio, String dataFim){
    String sql = "SELECT \n" +
"    'mini_curso' AS tipo,\n" +
"    mc.id AS id,\n" +
"    mc.nome AS nome,\n" +
"    mc.descricao AS descricao,\n" +
"    mc.data_horario AS data_horario,\n" +
"    mc.duracao AS duracao,\n" +
"    e.nome AS nome_evento\n" +
"FROM \n" +
"    mini_curso mc\n" +
"INNER JOIN \n" +
"    evento e ON mc.id_evento = e.id\n" +
"WHERE \n" +
"    e.id = :id_evento\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT \n" +
"    'palestra' AS tipo,\n" +
"    p.id AS id,\n" +
"    p.nome AS nome,\n" +
"    p.descricao AS descricao,\n" +
"    p.data_horario AS data_horario,\n" +
"    p.duracao AS duracao,\n" +
"    e.nome AS nome_evento\n" +
"FROM \n" +
"    palestra p\n" +
"INNER JOIN \n" +
"    evento e ON p.id_evento = e.id\n" +
"WHERE \n" +
"    e.id = :id_evento;";
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
            evento.setDescricao_evento(rs.getString("descricao_evento"));
            evento.setData_inic(rs.getTimestamp("data_inic"));
            evento.setData_fim(rs.getTimestamp("data_fim"));
            evento.setVagas_disp(rs.getInt("vagas_disp"));
            evento.setData_limite(rs.getTimestamp("data_limite"));
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

    public boolean removerEvento(int id){
        String sql = "DELETE FROM evento WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try{
            conn = ConnDao.conn();  

        stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        int result = stm.executeUpdate();
                
            if (result == 1) {
                 System.out.println("Deletado com sucesso");
                 return true;
             } else {
                 System.out.println("Nenhuma linha foi deletada");
             }
        
                            
        }catch(SQLException e){
            System.out.println("error" + e);
        }
        return false;
    }
    
    //TESTAR
   public boolean atualizarEvento(EventoModel evento, int id, Timestamp data_alt) {
    String sql = "UPDATE evento SET nome = ?, descricao_evento = ?, data_inic = ?, "
               + "data_fim = ?, vagas_disp = ?, data_limite = ?, nome_responsavel = ?, "
               + "cpf_responsavel = ?, email_responsavel = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stm = null;

    if (data_alt == null) {
        System.out.println("Data de alteração não pode ser nula.");
        return false;
    }

    // A data de alteração pode ser usada diretamente se for válida
    Timestamp data_att = new Timestamp(data_alt.getTime());

    try {
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);

        // Configura os parâmetros da consulta
        stm.setString(1, evento.getNome());
        stm.setString(2, evento.getDescricao_evento());

        // Para tratar com DateTime
        java.sql.Date data_inic = new java.sql.Date(evento.getData_inic().getTime());
        java.sql.Date data_fim = new java.sql.Date(evento.getData_fim().getTime());

        // Configura as datas
        stm.setDate(3, data_inic);
        stm.setDate(4, data_fim);

        stm.setInt(5, evento.getVagas_disp());

        java.sql.Date data_limite = new java.sql.Date(evento.getData_limite().getTime());
        stm.setDate(6, data_limite);

        stm.setString(7, evento.getNome_responsavel());
        stm.setString(8, evento.getCpf_responsavel());
        stm.setString(9, evento.getEmail_responsavel());

        stm.setInt(10, id);

        // Verifica se a data de alteração é válida antes de atualizar
        if (!data_att.before(data_inic)) {
            int result = stm.executeUpdate();

            if (result == 1) {
                System.out.println("Valores alterados com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar os valores.");
            }
        } else {
            System.out.println("A data de alteração não pode ser anterior à data de início do evento.");
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
                evento.setDescricao_evento(rs.getString("descricao_evento"));
                evento.setData_inic(rs.getTimestamp("data_inic"));
                evento.setData_fim(rs.getTimestamp("data_fim"));
                evento.setVagas_disp(rs.getInt("vagas_disp"));
                evento.setData_limite(rs.getTimestamp("data_limite"));
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
    
}
