package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.InscricaoController;
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
import java.sql.Timestamp;
import java.util.List;

@Path("inscricao/evento")
public class InscritosResource {
    
    private InscricaoController inscricaoController = new InscricaoController();
        
    //deu bom
    @POST
    @Autorizar
    @Path("{id_evento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public Response inscreverUsuario(InscricaoModel inscritos, @PathParam("id_evento") int id_evento) {
          boolean sucesso = inscricaoController.salvarInscricao(inscritos, id_evento);
        
        if (sucesso) {
            return Response.status(Response.Status.CREATED).entity("{\"msg\":\"Inscricao realizada com sucesso! Vamos estudar!.\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\":\"Erro ao realizar a inscrição.\"}").build();
        }
     }   
     
     
     //da bom
    @DELETE
     @Autorizar
    @Path("{cpf_usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerInscricaoEvento(@PathParam("cpf_usuario") String cpf_usuario, InscricaoModel inscritos) {
        boolean removido = inscricaoController.removerInscricaoEvento(inscritos, cpf_usuario);
        
        if (removido) {
            return Response
                    .ok("deletado com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"msg\":\"Erro ao deletar o evento ou evento não encontrado\"}")
                .build();
    }
    
    
    //deu bom
    @GET
    @Autorizar
    @Path("{id_evento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterUsuariosNoEvento(@PathParam("id_evento") int id_evento) {
        List<UsuarioModel> inscricaoEvento = inscricaoController.listarInscritosNoEvento(id_evento);
        
        if (inscricaoEvento.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"msg\":\"Nenhum usuário encontrado.\"}")
                           .build();
        }
        return Response.ok(inscricaoEvento).build();
    }
}
