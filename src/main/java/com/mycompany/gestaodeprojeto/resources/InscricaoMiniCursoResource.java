/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.InscricaoMiniCursoController;
import com.mycompany.gestaodeprojeto.Models.InscricaoMiniCursoModel;
import com.mycompany.gestaodeprojeto.Models.InscricaoModel;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import com.mycompany.gestaodeprojeto.security.Autorizar;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("inscricao/minicurso")
public class InscricaoMiniCursoResource {
    
    //arrumar tudo nesse aqui
    
    private InscricaoMiniCursoController inscricaoMiniCursoController = new InscricaoMiniCursoController();
    
    @POST
    @Autorizar
    @Path("{id_miniCurso}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public Response inscreverUsuario(InscricaoMiniCursoModel inscritoMiniCurso, @PathParam("id_miniCurso") int id_miniCurso, @PathParam("id_evento") int id_evento) {
          boolean sucesso = inscricaoMiniCursoController.salvarInscricao(inscritoMiniCurso, id_miniCurso, id_evento);
        
        if (sucesso) {
            return Response.status(Response.Status.CREATED).entity("Inscrição realizada com sucesso!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao realizar a inscrição.").build();
        }
     }   
     
    @DELETE
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cpf_usuario}")
    public Response removerInscricaoEvento(@PathParam("cpf_usuario") String cpf_usuario, InscricaoMiniCursoModel inscritoMiniCurso) {
        boolean removido = inscricaoMiniCursoController.removerInscricaoMiniCurso(inscritoMiniCurso, cpf_usuario);
        
        if (removido) {
            return Response
                    .ok("deletado com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("Erro ao deletar o evento ou evento não encontrado")
                .build();
    }
    
     @GET
    @Autorizar
    @Path("{id_minicurso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterUsuariosNoMiniCurso(@PathParam("id_minicurso") int id_minicurso) {
        List<UsuarioModel> inscricaoModel = inscricaoMiniCursoController.listarInscritosNoMiniCurso(id_minicurso) ;
        if (inscricaoModel.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Nenhum usuário encontrado.")
                           .build();
        }
        return Response.ok(inscricaoModel).build();
    }
    
}
