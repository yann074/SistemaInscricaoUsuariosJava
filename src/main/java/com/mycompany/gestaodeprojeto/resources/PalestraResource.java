package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.PalestraController;
import com.mycompany.gestaodeprojeto.Models.PalestraModel;
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

/**
 *
 * @author yanns
 */
@Path("palestra")
public class PalestraResource {
    
    private PalestraController palestraController = new PalestraController();
    
    //deu bom, arrumar
    @POST
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPalestra(PalestraModel palestra){
        boolean salvar = palestraController.salvarPalestra(palestra);
       
        if(salvar){
            return Response
                    .status(Response.Status.CREATED)
                    .entity("Palestra salva com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Ocorreu um erro ao salvar a palestra")
                .build();
    }
    
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterPalestras(){
        List<PalestraModel> palestras = palestraController.listarPalestras();
        
        if(palestras.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma palestra encontrada")
                    .build();
        }
        
        return Response
                .ok(palestras)
                .build();
    }
    
    @DELETE
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response removerPalestra(@PathParam("id") int id){
        boolean removido = palestraController.removerPalestra(id);
        
        if(removido){
            return Response
                    .status(Response.Status.OK)
                    .entity("Palestra removida com sucesso!")
                    .build();
        }
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao remover a palestra")
                .build();
    }
    
    @PUT
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response atualizarPalestra(@PathParam("id") int id, PalestraModel palestra){
        boolean atualizado = palestraController.atualizarPalestra(palestra, id);
        
        if(atualizado){
            return Response
                    .status(Response.Status.OK)
                    .entity("Palestra com ID " + id + " atualizada com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Erro ao atualizar a palestra. Verifique os dados.")
                .build();
    }
}
