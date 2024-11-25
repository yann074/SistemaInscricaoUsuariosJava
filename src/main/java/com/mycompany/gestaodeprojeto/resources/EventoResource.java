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
import java.sql.Timestamp;
import java.util.List;

@Path("evento")
public class EventoResource {
    private EventoController eventoController = new EventoController();

    // Salvar evento
    @POST
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarEvento(EventoModel evento) {
        boolean salvar = eventoController.salvarEvento(evento);

        if (salvar) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("{\"msg\":\"Evento salvo com sucesso\"}")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"msg\":\"Ocorreu um erro ao salvar o evento\"}")
                .build();
    }

    // Listar eventos com filtro de datas
    @GET
    @Autorizar
    @Path("{dataInicio}/{dataFim}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterEventos(@PathParam("dataInicio") String dataInicio, @PathParam("dataFim") String dataFim) {
        List<EventoModel> eventos = eventoController.listarEventos(dataInicio, dataFim);

        if (eventos.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Nenhum evento encontrado.\"}")
                    .build();
        }

        return Response
                .ok(eventos)
                .build();
    }

    // Obter programação do evento
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/programacao/{id}")
    public Response obterProgramacaoEvento(@PathParam("id") int id) {
        List<EventoModel> evento = eventoController.listarProgramacaoEvento(id);
        
        if (evento == null || evento.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Evento não encontrado\"}")
                    .build();
        }

        return Response
                .ok(evento)
                .build();
    }

    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/programacao/evento/{id}")
    public Response listarEventoComPalestraEMiniCurso(@PathParam("id") int id) {
        List<EventoModel> evento = eventoController.listarEventoComPalestraEMiniCurso(id);
        
        if (evento == null || evento.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Evento não encontrado\"}")
                    .build();
        }

        return Response
                .ok(evento)
                .build();
    }
    
    // Remover evento
    @DELETE
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response removerEvento(@PathParam("id") int id) {
        boolean removido = eventoController.removerEvento(id);
        
        if (removido) {
            return Response
                    .ok("{\"msg\":\"Evento deletado com sucesso\"}")
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"msg\":\"Erro ao deletar o evento ou há participantes no evento, ou há palestras/miniCursos iniciados\"}")
                .build();
    }

    // Atualizar evento
    @PUT
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response atualizarEvento(@PathParam("id") int id, EventoModel evento) {
        boolean atualizado = eventoController.atualizarEvento(evento, id);

        if (atualizado) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"Evento atualizado com sucesso\"}")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"msg\":\"Erro ao atualizar o evento ou dados inválidos\"}")
                .build();
    }
}
