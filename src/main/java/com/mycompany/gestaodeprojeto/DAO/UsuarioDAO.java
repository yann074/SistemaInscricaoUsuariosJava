/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;

/**
 *
 * @author yanns
 */
public class UsuarioDAO {
       

    
    public boolean salvarUsuario(UsuarioModel usuario){
        String sql = "INSERT INTO usuario(cpf,nome,email,senha) VALUES (?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, usuario.getCpf());
            stm.setString(2, usuario.getNome());
            stm.setString(3, usuario.getEmail());
            stm.setString(4, usuario.getSenha());
            
            stm.executeUpdate();
            
            return true;
           
        }catch(SQLException e){
            System.out.println("Nao foi possivel salvar"+ e.getMessage());
            return false;
        }finally{
            try{
                if(stm != null){
                    stm.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public List<UsuarioModel> AparecerUsuarios() {
    String sql = "SELECT * FROM usuario";
    List<UsuarioModel> usuarios = new ArrayList<>();
    
    Connection conn = null;
    Statement stm = null;
    
    try {
        conn = ConnDao.conn();
        stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery(sql);
        
        while (rs.next()) {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setCpf(rs.getString("cpf"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
             usuario.setIs_adm(rs.getBoolean("is_adm"));
            
            usuarios.add(usuario); 
        }
        
    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuários: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    return usuarios;
}
    


    public boolean atualizarUsuario(UsuarioModel usuario, String cpf) {

    try {
       
        
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);

            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.setString(3, usuario.getSenha());
            stm.setString(4, cpf);

            int result = stm.executeUpdate();

            if (result == 1) {
                System.out.println("Usuário atualizado com sucesso.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao validar o token: " + e.getMessage());
        return false;
    }
        return false;
}

    
    public boolean VerificarCPFEEmail(UsuarioModel usuario){
        String sql = "SELECT * FROM usuario WHERE cpf = ? OR email = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, usuario.getCpf());
            stm.setString(2 , usuario.getEmail());
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
               System.out.println("Não é possivel salvar, ja existe dados com esses parametros"); 
            }else{
                System.out.println("Salvo com sucesso");
                }
            
            stm.close();
            conn.close();
                        
            }catch(SQLException e){
            System.out.println("Nao foi possivel salvar, tem Email ou CPF igual "+ e.getMessage());
            
        }
        
        return true;
           
    }
    
    public boolean removerUsuario(String cpf) {
    if (removerInscricoes(cpf)) {
        String sql = "DELETE FROM usuario WHERE cpf = ?";
        
        try (Connection conn = ConnDao.conn();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            
            stm.setString(1, cpf);
            
            int result = stm.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    } else {
        System.out.println("Erro ao remover as inscrições do usuário.");
        return false;
    }
}

    //aq foi gpt msm
public boolean removerInscricoes(String cpf) {
    String sql = "DELETE FROM inscricao WHERE cpf_usuario = ?";
    
    try (Connection conn = ConnDao.conn();
         PreparedStatement stm = conn.prepareStatement(sql)) {
        
        stm.setString(1, cpf);
        
        int result = stm.executeUpdate();
        return result >= 0;  
    } catch (SQLException e) {
        System.out.println("Erro ao remover inscrições: " + e.getMessage());
        return false;
    }
}

    

     
     public boolean loginUsuario(UsuarioModel usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        conn = ConnDao.conn();
        stm = conn.prepareStatement(sql);
        stm.setString(1, usuario.getCpf());
        stm.setString(2, usuario.getSenha());
        
        rs = stm.executeQuery();
        
        // Se o usuário for encontrado no banco de dados, retorna true
        return rs.next();
    } catch (SQLException e) {
        System.out.println("Erro ao consultar usuário: " + e.getMessage());
        throw e; // Propaga a exceção para a camada superior
    } finally {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
        if (conn != null) conn.close();
    }
}


    
//agr essa porra deu certo, graças a Deus
public boolean promoverUsuario(String cpf, boolean is_adm){
    String sql = "SELECT * FROM usuario WHERE cpf = ? AND is_adm = 0 ";

        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, cpf);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                
               System.out.println("chegou aqui");
               String sqlup = "UPDATE usuario SET is_adm = 1 WHERE cpf = ?";
               
               PreparedStatement stmt = null;
               
               stmt = conn.prepareStatement(sqlup);
               
                stmt.setString(1, cpf);
               
               int resultup = stmt.executeUpdate();
               
               if(resultup == 1){
                   System.out.println("Atualizado com sucesso");
                   
                   return true;
               }
            }

        }catch(SQLException e){
            System.out.println("error" + e);
        }
        return false;
        }

        public boolean usuarioIsAdm(String cpf){
            String sql = "SELECT is_adm FROM usuario WHERE cpf = ?";

            
            Connection conn = null;
            PreparedStatement stm = null;
            
            try{
                conn = ConnDao.conn();
                stm = conn.prepareStatement(sql);
                stm.setString(1, cpf); 
                       
                ResultSet rs = stm.executeQuery();
                
                if(rs.next()){
                     boolean isAdm = rs.getBoolean("is_adm");
                       if (isAdm) {
                        System.out.println("Deu bom, ele é adm");
                        return true;
                    }
                }
                
            }catch(SQLException e){
                System.out.println("DEU ERRADOOOOO, isso é mt dificil, nmrl" + e.getMessage());
            }
            
            return false;
                        
        }

}
