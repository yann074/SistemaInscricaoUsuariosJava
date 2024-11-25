package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.MiniCursoController;
import com.mycompany.gestaodeprojeto.Models.MiniCursoModel;
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
import java.util.Date;
import java.util.List;

@Path("minicurso")
public class MiniCursoResource {
    private MiniCursoController minicursoController = new MiniCursoController();

    //deu bom
    @POST
    @Autorizar
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarMiniCurso(MiniCursoModel minicurso) {
        boolean salvar = minicursoController.salvarMiniCurso(minicurso);

        if (salvar) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("{\"msg\":\"Mini Curso criado com sucesso\"}")
                    .build();
        }

        // Retorno de erro com formato específico
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"msg\":\"Ocorreu um erro ao salvar o MiniCurso\"}")
                .build();
    }

    //deu bom
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterMiniCurso() {
        List<MiniCursoModel> minicursos = minicursoController.listarMiniCurso();
        
        if (minicursos.isEmpty()) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .ok(minicursos)
                .build();
    }

    //deu bom
    @DELETE
    @Autorizar
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerMiniCurso(@PathParam("id") int id, Timestamp data_alt) {
        boolean removido = minicursoController.removerMiniCurso(id, data_alt);

        if (removido) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"MiniCurso removido com sucesso!\"}")
                    .build();
        }

        // Retorno de erro com formato específico
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"msg\":\"MiniCurso não encontrado ou erro ao remover.\"}")
                .build();
    }

    //deu bom
    @PUT
    @Autorizar
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarMiniCurso(@PathParam("id") int id, MiniCursoModel minicurso, Date data_alt) {
        boolean atualizado = minicursoController.atualizarMiniCurso(minicurso, id, data_alt);

        if (atualizado) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"MiniCurso atualizado com sucesso!\"}")
                    .build();
        }

        // Retorno de erro com formato específico
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"msg\":\"Erro ao atualizar o MiniCurso. Verifique os dados.\"}")
                .build();
    }
}
