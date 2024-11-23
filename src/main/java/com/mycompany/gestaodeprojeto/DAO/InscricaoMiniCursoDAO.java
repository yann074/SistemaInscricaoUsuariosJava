/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Models.InscricaoMiniCursoModel;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yanns
 */
public class InscricaoMiniCursoDAO {    
    
    public boolean salvarInscricao(InscricaoMiniCursoModel inscritoMiniCurso, int id_miniCurso, int id_evento){
        String sql = "INSERT INTO inscricao_minicurso (cpf_usuario, id_mini_curso, data_inscricao) VALUES (?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, inscritoMiniCurso.getCpf_usuario());
            stm.setInt(2, id_miniCurso);
            
            
            Date dataInscricao = inscritoMiniCurso.getData_inscricao();
            Timestamp timestamp = new Timestamp(dataInscricao.getTime()); 

            stm.setTimestamp(3, timestamp); 

            int resultado = stm.executeUpdate();
            
            if (resultado == 1) {
                System.out.println("Inscrição realizada com sucesso!");
            }
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar inscrição: " + e.getMessage());
        }
        
        return false;
    }
    public boolean removerInscricaoEvento(InscricaoMiniCursoModel inscritoMiniCurso, String cpf_usuario){
        String sql = "DELETE FROM inscricao_minicurso WHERE cpf_usuario = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, cpf_usuario);
            
            int resultado = stm.executeUpdate();
            
            if (resultado == 1) {
                System.out.println("Inscrição realizada com sucesso!");
                return true;
            }
            
            } catch (SQLException e) {
            System.out.println("Erro ao salvar inscrição: " + e.getMessage());
        }
        
        return false;
    }
    
     public List<UsuarioModel> listarInscritosNoMiniCurso(int id_minicurso){
            String sql = "SELECT inscricao_minicurso.cpf_usuario, usuario.nome, usuario.email " +
            "FROM inscricao_minicurso " +
            "JOIN usuario ON inscricao_minicurso.cpf_usuario = usuario.cpf " +
            "WHERE inscricao_minicurso.id_mini_curso = ?";

        List<UsuarioModel> inscricaoMiniCurso = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1,id_minicurso);
           
            ResultSet rs = stm.executeQuery();
            
                        
            while(rs.next()){
                UsuarioModel usuario = new UsuarioModel();
                
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                
                inscricaoMiniCurso.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("error " + e);        
    }          
        return inscricaoMiniCurso;
    }
    
}
