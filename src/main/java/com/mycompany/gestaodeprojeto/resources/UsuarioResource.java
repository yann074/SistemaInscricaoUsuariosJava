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
        
    //falta implementar para so o adm fazer as coisas
    //codigo sem erro
    
    
    private UsuarioController usuarioController = new UsuarioController();

    // Ta certo
    @GET
    @Autorizar
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterUsuarios() {
        List<UsuarioModel> usuarios = usuarioController.AparecerUsuario(null);
        if (usuarios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Nenhum usuário encontrado.")
                           .build();
        }
        return Response.ok(usuarios).build();
    }

    // RODOU O DO DOCUMENTO
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioModel usuario) {
        boolean verificarRepitido = usuarioController.SalvarUsuario(usuario);

        if (!verificarRepitido) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Já existe um usuário com esse CPF ou email. Por favor, escolha outro.")
                           .build();
        }

        return Response.status(Response.Status.CREATED)
                       .entity("Usuário salvo com sucesso!")
                       .build();
    }

    // DEU BOM O DOCUMENTO POREM É PELO CPF
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
        return Response.status(Response.Status.FORBIDDEN).entity("Você não pode alterar os dados de outro usuário").build();
    }

    // Atualizar dados do usuário
    boolean atualizado = usuarioController.atualizarUsuario(usuario, cpf);
    if (atualizado) {
        return Response.ok("Usuário atualizado com sucesso!").build();
    } else {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar os dados do usuário").build();
    }
} catch (Exception e) {
    // Log o erro para depuração
    System.err.println("Erro: " + e.getMessage());
    return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido ou expirado").build();
}

    }

    //RODOU DOCUMENTO POREM COM CPF
    @DELETE
    @Autorizar
    @Path("{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerUsuario(@PathParam("cpf") String cpf) {
        boolean sucesso = usuarioController.removerUsuario(cpf);
        if (sucesso) {
            return Response.ok("{\"mensagem\":\"Usuário deletado com sucesso!\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"mensagem\":\"Erro ao deletar o usuário. Usuário não encontrado.\"}")
                           .build();
        }
    }

    // RODOU O DOCUMENTO
   @POST
@Path("/logar")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public Response loginUsuario(UsuarioModel usuario) {
    try {
        UsuarioController user = new UsuarioController();
        String jwtToken = user.loginUsuario(usuario); // Gera o JWT
        return Response.ok("Token gerado: " + jwtToken).build(); // Retorna o JWT
    } catch (Exception ex) {
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity("error: " + ex.getMessage()) // Exibe a mensagem do erro
                       .build();
    }
}

    // Promove com sucesso, mesmo retornando erro e promove pelo cpf
    @POST
    @Autorizar
    @Path("/promover/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response promoverUsuario(@PathParam("cpf") String cpf) {
        boolean promover = usuarioController.promoverUsuario(cpf, false);
        if (promover) {
            return Response.ok("Usuário promovido a administrador com sucesso!").build();
        } else {
            return Response
                           .ok("Erro. Tente novamente.")
                           .build();
        }
    }
}