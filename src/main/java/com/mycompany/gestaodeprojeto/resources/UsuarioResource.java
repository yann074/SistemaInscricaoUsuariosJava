package com.mycompany.gestaodeprojeto.resources;

import com.mycompany.gestaodeprojeto.Controller.UsuarioController;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import com.mycompany.gestaodeprojeto.security.Autorizar;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("usuario")
public class UsuarioResource {

    private UsuarioController usuarioController = new UsuarioController();

    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterUsuarios() {
        List<UsuarioModel> usuarios = usuarioController.AparecerUsuario(null);
        if (usuarios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"msg\": \"Nenhum usuário encontrado.\"}")
                           .build();
        }
        return Response.ok(usuarios).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioModel usuario) {
        boolean verificarRepitido = usuarioController.SalvarUsuario(usuario);

        if (!verificarRepitido) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"msg\": \"Já existe um usuário com esse CPF ou email. Por favor, escolha outro.\"}")
                           .build();
        }

        return Response.status(Response.Status.CREATED)
                       .entity("{\"msg\": \"Usuário salvo com sucesso!\"}")
                       .build();
    }

    @PUT
    @Path("{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("cpf") String cpf, UsuarioModel usuario, @HeaderParam("Authorization") String authHeader) {
        String token = authHeader.substring("Bearer ".length());

        try {
            Claims claims = usuarioController.parseToken(token);
            String usuarioCpf = claims.getSubject();  

            // Log para verificação
            System.out.println("CPF do token: " + usuarioCpf);

            if (!usuarioCpf.equals(cpf)) {
                return Response.status(Response.Status.FORBIDDEN)
                               .entity("{\"msg\": \"Você não pode alterar os dados de outro usuário\"}")
                               .build();
            }

            // Atualizar dados do usuário
            boolean atualizado = usuarioController.atualizarUsuario(usuario, cpf);
            if (atualizado) {
                return Response.ok("{\"msg\": \"Usuário atualizado com sucesso!\"}").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                               .entity("{\"msg\": \"Erro ao atualizar os dados do usuário\"}")
                               .build();
            }
        } catch (Exception e) {
            // Log o erro para depuração
            System.err.println("Erro: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("{\"msg\": \"Token inválido ou expirado\"}")
                           .build();
        }
    }
    
    
@DELETE
@Autorizar
@Path("{cpf}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response removerUsuario(@PathParam("cpf") String cpf) {
    System.out.println("Tentando deletar o usuário com CPF: " + cpf); 

    if (!usuarioController.UsuarioIsAdm(cpf)) {
        return Response.status(Response.Status.FORBIDDEN)
                       .entity("{\"msg\": \"Apenas Administradores podem realizar essa ação.\"}")
                       .build();
    }

    boolean sucesso = usuarioController.removerUsuario(cpf);

    if (sucesso) {
        return Response.ok("{\"msg\": \"Usuário deletado com sucesso!\"}").build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("{\"msg\": \"Erro ao deletar o usuário. Usuário não encontrado.\"}")
                       .build();
    }
}



    @POST
    @Path("/logar")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUsuario(UsuarioModel usuario) {
        try {
            UsuarioController user = new UsuarioController();
            String jwtToken = user.loginUsuario(usuario); // Gera o JWT
            return Response.ok("{\"msg\": \"Token gerado: " + jwtToken + "\"}").build(); // Retorna o JWT
        } catch (Exception ex) {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("{\"msg\": \"Erro: " + ex.getMessage() + "\"}") // Exibe a mensagem do erro
                           .build();
        }
    }

    @POST
    @Autorizar
    @Path("/promover/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response promoverUsuario(@PathParam("cpf") String cpf) {
        boolean promover = usuarioController.promoverUsuario(cpf, false);
        
         if (!usuarioController.UsuarioIsAdm(cpf)) {
        return Response.status(Response.Status.FORBIDDEN)
                       .entity("{\"msg\": \"Apenas Administradores podem realizar essa ação.\"}")
                       .build();
    }
         
        if (promover) {
            return Response.ok("{\"msg\": \"Usuário promovido a administrador com sucesso!\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"msg\": \"Erro. Tente novamente.\"}")
                           .build();
        }
    }
}
