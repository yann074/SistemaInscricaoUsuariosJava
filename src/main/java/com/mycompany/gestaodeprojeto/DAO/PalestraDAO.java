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
import java.util.List;

/**
 *
 * @author yanns
 */
public class PalestraDAO {
    
    
        //falta inserir nome do palestrante e minicursopalestrante
    public boolean salvarPalestra(PalestraModel palestra){
        String sql = "INSERT INTO palestra (nome_palestra, descricao_palestra, data_inicio_palestra, data_fim_palestra, vagas_disp, data_limite) VALUES (?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, palestra.getNomePalestra());
            stm.setString(2, palestra.getDescricaoPalestra());  
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(palestra.getDataInicioPalestra().getTime());
            java.sql.Date data_fim = new java.sql.Date(palestra.getDataFimPalestra().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
            
            stm.setString(5, palestra.getVagasDisp());
            
            java.sql.Date data_limite = new java.sql.Date(palestra.getDataLimite().getTime());
            
            stm.setDate(6, data_limite);
            
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
                
                palestra.setNomePalestra(rs.getString("nome_palestra"));
                palestra.setDescricaoPalestra(rs.getString("descricao_palestra"));
                palestra.setDataInicioPalestra(rs.getTimestamp("data_inicio_palestra"));
                palestra.setDataFimPalestra(rs.getTimestamp("data_fim_palestra"));
                
                palestras.add(palestra);
            }
        }catch(SQLException e){
            System.out.println("error " + e);        
    }          
    return palestras;
    }
    

   public boolean removerPalestra(int id) {
    String sqlSelect = "SELECT id FROM palestra WHERE id = ?";
    String sqlDelete = "DELETE FROM palestra WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar se a palestra existe
        stm = conn.prepareStatement(sqlSelect);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
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

    public boolean atualizarPalestra(PalestraModel palestra, int id){
        String sql = "UPDATE palestra SET nome_palestra = ?, descricao_palestra = ?, data_inicio_palestra = ?, "
                     + " data_fim_palestra = ?, vagas_disp = ?, data_limite = ?  WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, palestra.getNomePalestra());
            stm.setString(2, palestra.getDescricaoPalestra());  
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(palestra.getDataInicioPalestra().getTime());
            java.sql.Date data_fim = new java.sql.Date(palestra.getDataFimPalestra().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
            
            stm.setString(5, palestra.getVagasDisp());
            
            java.sql.Date data_limite = new java.sql.Date(palestra.getDataLimite().getTime());
            
            stm.setDate(6, data_limite);
            
            stm.setInt(7, id);
            
            int resultado = stm.executeUpdate();
            
            if(resultado == 1){
                System.out.println("Valores alterados com sucesso");
                return true;
            }else if(resultado == 0){
                System.out.println("Algo deu errado");
            }
            
        }catch(SQLException e){
            System.out.println("Ocorreu um error" + e);
        }
        return false;
    }
    
    
}
