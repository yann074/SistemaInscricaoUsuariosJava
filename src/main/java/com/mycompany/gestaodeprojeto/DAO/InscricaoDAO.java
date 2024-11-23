package com.mycompany.gestaodeprojeto.DAO;

import com.mycompany.gestaodeprojeto.Models.InscricaoModel;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscricaoDAO {
    //TESTAR
    public boolean salvarInscricao(InscricaoModel inscritos, int id_evento, Timestamp data_atl){
        String sql = "INSERT INTO inscricao (cpf_usuario, id_evento, data_inscricao) VALUES (?, ?, ?)";
        String sqldata = "SELECT data_inic FROM evento WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        PreparedStatement stmselect = null;
        ResultSet rs = null;
        
        try {
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stmselect = conn.prepareStatement(sqldata);
            stmselect.setInt(1, id_evento);
        
            rs = stmselect.executeQuery();
            
            if(rs.next()){
                 Timestamp data_inic = rs.getTimestamp("data_inic");    
                 
                 if(data_atl.before(data_inic)){
                      
                stm.setString(1, inscritos.getCpf_usuario());
                stm.setInt(2, id_evento);

                Date dataInscricao = inscritos.getData_inscricao();
                Timestamp timestamp = new Timestamp(dataInscricao.getTime()); 

                stm.setTimestamp(3, timestamp); 
                
                    int resultado = stm.executeUpdate();

                    if (resultado == 1) {
                        System.out.println("Inscrição realizada com sucesso!");
                    }

                 }
            }
           
       
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar inscrição: " + e.getMessage());
        }
        
        return false;
    }
    
   public boolean removerInscricaoEvento(InscricaoModel inscritos, String cpf_usuario) {
    String sqlSelect = "SELECT e.data_inic FROM inscricao i " +
                       "JOIN evento e ON i.id_evento = e.id " +
                       "WHERE i.cpf_usuario = ?";
    String sqlDelete = "DELETE FROM inscricao WHERE cpf_usuario = ?";
    
    Connection conn = null;
    PreparedStatement stmSelect = null;
    PreparedStatement stmDelete = null;
    ResultSet rs = null;

    try {
        conn = ConnDao.conn();

        stmSelect = conn.prepareStatement(sqlSelect);
        stmSelect.setString(1, cpf_usuario);
        rs = stmSelect.executeQuery();

        if (rs.next()) {
            Timestamp dataInicioEvento = rs.getTimestamp("data_inic");
            Timestamp agora = new Timestamp(System.currentTimeMillis());

            long diferencaHoras = (dataInicioEvento.getTime() - agora.getTime()) / (1000 * 60 * 60);
            
            if (diferencaHoras >= 24) {
                
                stmDelete = conn.prepareStatement(sqlDelete);
                stmDelete.setString(1, cpf_usuario);
                int resultado = stmDelete.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Inscrição removida com sucesso!");
                    return true;
                } else {
                    System.out.println("Nenhuma inscrição foi removida.");
                }
            } else {
                System.out.println("A remoção da inscrição só pode ser feita pelo menos 24 horas antes do início do evento.");
            }
        } else {
            System.out.println("Inscrição ou evento não encontrado.");
        }

    } catch (SQLException e) {
        System.out.println("Erro ao remover inscrição: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmSelect != null) stmSelect.close();
            if (stmDelete != null) stmDelete.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e);
        }
    }

    return false;
}

   
    public List<UsuarioModel> listarInscritosNoEvento(int id_evento){
            String sql = "SELECT inscricao.cpf_usuario, usuario.nome, usuario.email " +
            "FROM inscricao " +
            "JOIN usuario ON inscricao.cpf_usuario = usuario.cpf " +
            "WHERE inscricao.id_evento = ?";



        
        //id_evento
        
        List<UsuarioModel> inscricaoEvento = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
            conn = ConnDao.conn();
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1,id_evento);
           
            ResultSet rs = stm.executeQuery();
            
                        
            while(rs.next()){
                UsuarioModel usuario = new UsuarioModel();
                
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                
                inscricaoEvento.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("error " + e);        
    }          
        return inscricaoEvento;
    }
    
    //verificar se a data da inscricao é antes da data inicial do evento pegar o where e o new date()
}
