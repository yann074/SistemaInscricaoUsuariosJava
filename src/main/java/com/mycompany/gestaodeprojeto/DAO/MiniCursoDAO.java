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
import java.util.List;

public class MiniCursoDAO {
    
    //nome_instrutor e minicurriculo
    public boolean salvarMiniCurso(MiniCursoModel miniCurso){
        String sql = "INSERT INTO mini_curso (nome_minicurso, descricao_minicurso, data_inic_minicurso, data_fim_minicurso, "
                + "vagas_disp_minicurso, data_limite_minicurso) VALUES (?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, miniCurso.getNome_minicurso());
            stm.setString(2, miniCurso.getDescricao_minicurso());  
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(miniCurso.getData_inic_minicurso().getTime());
            java.sql.Date data_fim = new java.sql.Date(miniCurso.getData_fim_minicurso().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
                        
            stm.setInt(5, miniCurso.getVagas_disp_minicurso());
            
            //Para tratar com
            java.sql.Date data_limite = new java.sql.Date(miniCurso.getData_limite_minicurso().getTime());
            
            stm.setDate(6, data_limite);
            
            int resultado = stm.executeUpdate();
            
            if(resultado == 1){
                System.out.println("Deu bom");
                return true;
            }
            
        }catch(SQLException e){
             System.out.println("error " + e.getMessage());
             return false;
        }
        return true;
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
                
                minicurso.setNome_minicurso(rs.getString("nome_minicurso"));
                minicurso.setDescricao_minicurso(rs.getString("descricao_minicurso"));
                minicurso.setData_inic_minicurso(rs.getTimestamp("data_inic_minicurso"));
                minicurso.setData_inic_minicurso(rs.getTimestamp("data_fim_minicurso"));
                minicurso.setVagas_disp_minicurso(rs.getInt("vagas_disp_minicurso"));
                minicurso.setData_inic_minicurso(rs.getTimestamp("data_limite_minicurso"));
                
                minicursos.add(minicurso);
                
            }
        }catch(SQLException e){
            System.out.println("Deu errrado: " + e);
        }
        return minicursos;
        
    }
    
   public boolean removerMiniCurso(int id, Timestamp data_alt) {
    String sqlSelect = "SELECT data_inic FROM mini_curso WHERE id = ?";
    String sqlDelete = "DELETE FROM mini_curso WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        // Estabelecer conexão
        conn = ConnDao.conn();

        // Verificar a data inicial do curso
        stm = conn.prepareStatement(sqlSelect);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            Timestamp data_inic = rs.getTimestamp("data_inic");

            if (data_alt.before(data_inic)) {
                stm = conn.prepareStatement(sqlDelete);
                stm.setInt(1, id);
                int result = stm.executeUpdate();

                if (result == 1) {
                    System.out.println("Deletado com sucesso");
                    return true;
                } else {
                    System.out.println("Nenhuma linha foi deletada");
                }
            } else {
                System.out.println("Data de alteração não é anterior à data inicial");
            }
        } else {
            System.out.println("Curso não encontrado");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao remover mini curso: " + e.getMessage());
    }
    return false;
}

    
    //depois juntar com cpf do usuario
    public boolean atualizarMiniCurso(MiniCursoModel miniCurso, int id){
        String sql = "UPDATE mini_curso SET nome_minicurso = ?, descricao_minicurso = ?, data_inic_minicurso = ?, "
                + "data_fim_minicurso = ?, vagas_disp_minicurso = ?, data_limite_minicurso = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
             stm.setString(1, miniCurso.getNome_minicurso());
            stm.setString(2, miniCurso.getDescricao_minicurso());  
            
            //Para tratar com DateTime            
            java.sql.Date data_inic = new java.sql.Date(miniCurso.getData_inic_minicurso().getTime());
            java.sql.Date data_fim = new java.sql.Date(miniCurso.getData_fim_minicurso().getTime());
            
            //para setar as datas
            stm.setDate(3, data_inic);
            stm.setDate(4, data_fim);
                        
            stm.setInt(5, miniCurso.getVagas_disp_minicurso());
            
            //Para tratar com
            java.sql.Date data_limite = new java.sql.Date(miniCurso.getData_limite_minicurso().getTime());
            
            stm.setDate(6, data_limite);
            stm.setInt(7, id);

            
            int resultado = stm.executeUpdate();
            
            if(resultado == 1){
                
                System.out.println("Atualizado com sucesso");
                
            return true;
                /*return Response
                        .ok("Alterado com sucesso")
                        .build(); */

            }else if (resultado == 0){
                System.out.println("Algo deu errado");
            return false;
            }
            
        }catch(SQLException e){
            System.out.println("Error" + e);
            
        }
        return false;
        
    }
    
}
