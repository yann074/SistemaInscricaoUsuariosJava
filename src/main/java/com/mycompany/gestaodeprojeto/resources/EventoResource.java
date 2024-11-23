package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.EventoController;
import com.mycompany.gestaodeprojeto.Models.EventoModel;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Path("evento")
public class EventoResource {
    private EventoController eventoController = new EventoController();

    //ta salvando
    @POST
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarEvento(EventoModel evento) {
        boolean salvar = eventoController.salvarEvento(evento);

        if (salvar) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("Evento salvo com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Ocorreu um erro ao salvar o evento")
                .build();
    }

    //Gera com um filtro de Datas
    @GET
    @Autorizar
    @Path("{dataInicio}/{dataFim}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterEventos(@PathParam("dataInicio") String dataInicio,@PathParam("dataFim") String dataFim) {
        List<EventoModel> eventos = eventoController.listarEventos( dataInicio,  dataFim);
        return Response
                .ok(eventos)
                .build();
    }
    
    //deu certo
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/programacao/{id}")
    public Response obterProgramacaoEvento(@PathParam("id") int id) {
        List<EventoModel> evento = eventoController.listarProgramacaoEvento(id);
        
        if (evento == null || evento.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Evento não encontrado")
                    .build();
        }

        return Response
                .ok(evento)
                .build();
    }

    //RODA
    @DELETE
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response removerEvento(@PathParam("id") int id) {
        boolean removido = eventoController.removerEvento(id);
        
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

    
    //TESTAR
    @PUT
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response atualizarEvento(@PathParam("id") int id, EventoModel evento, Timestamp data_alt) {
        boolean atualizado = eventoController.atualizarEvento(evento, id, data_alt);

        if (atualizado) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Evento atualizado com sucesso")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Erro ao atualizar o evento ou dados inválidos")
                .build();
    }
}
