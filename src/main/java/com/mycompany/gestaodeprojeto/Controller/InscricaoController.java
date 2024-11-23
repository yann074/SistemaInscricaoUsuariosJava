/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.InscricaoDAO;
import com.mycompany.gestaodeprojeto.Models.InscricaoModel;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author yanns
 */
public class InscricaoController {
    private InscricaoDAO inscricaoDAO;

    public InscricaoController() {
        this.inscricaoDAO = new InscricaoDAO();
    }
    
    public boolean salvarInscricao(InscricaoModel inscrito, int id_evento, Timestamp data_atl){
        return inscricaoDAO.salvarInscricao(inscrito,id_evento, data_atl);
    }
    
    public boolean removerInscricaoEvento(InscricaoModel inscritos, String cpf_usuario){
        return inscricaoDAO.removerInscricaoEvento(inscritos, cpf_usuario);
    }
 
    public List<UsuarioModel> listarInscritosNoEvento(int id_evento){
        return inscricaoDAO.listarInscritosNoEvento(id_evento);
    }
}


/*

 Não será permitida a remoção de eventos que possuam participantes inscritos;  FAZER O TESTE

As únicas funcionalidades que um usuário comum pode fazer é alterar seus próprios dados, realizar sua
inscrição em um evento e acessar os endpoints de listagem;

//PUXAR UM ID DO EVENTO PARA BUSCAR O MINI CURSO, PALESTRA TAMBEM

FAZER A PARTE DE DATA QUE FIZ NO EVENTO FAZER NO MINI CURSO E NA PALESTRA

        try {
            if (dados.get("nome") == null) {
                return Response.status(Response.Status.UNPROCESSABLE_ENTITY)
                        .entity(Collections.singletonMap("msg", "Campo 'nome' é obrigatório"))
                        .build();
            }
            // Lógica para salvar o recurso
            return Response.status(Response.Status.CREATED)
                    .entity(Collections.singletonMap("msg", "Recurso criado com sucesso"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Collections.singletonMap("msg", "Erro interno no servidor"))
                    .build();
        }
    }
*/