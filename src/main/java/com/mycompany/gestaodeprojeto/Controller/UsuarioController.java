/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.UsuarioDAO;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;

/**
 *
 * @author yanns
 */
public class UsuarioController {
    
    private UsuarioDAO UsuarioDao;
    private final static SecretKey CHAVE = Keys.hmacShaKeyFor(
        "essachavevaiseramaisdificildomundoeniguemalemdeyannvaiacertartmj"
        .getBytes(StandardCharsets.UTF_8));

public String validarToken(String token) throws Exception {
    try {
        // Usando o parserBuilder para parsear o token
        String cpf = Jwts.parserBuilder()
                .setSigningKey(CHAVE)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Obtém o CPF do assunto (subject) do token
        
        return cpf; // Retorna o CPF extraído do token
    } catch (Exception e) {
        throw new Exception("Erro ao validar o token: " + e.getMessage());
    }
}

    
public static Claims parseToken(String token) throws Exception {
    if (token == null || token.isEmpty()) {
        throw new Exception("Token não fornecido ou está vazio");
    }
    try {
        return Jwts.parserBuilder()
                   .setSigningKey(CHAVE)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    } catch (Exception e) {
        throw new Exception("Erro ao parsear o token: " + e.getMessage());
    }
}


    public UsuarioController() {
        this.UsuarioDao = new UsuarioDAO();
    }
    
    
    public boolean UsuarioIsAdm(String cpf){
        return UsuarioDao.usuarioIsAdm(cpf);
    }
    public boolean SalvarUsuario(UsuarioModel usuario){
        if(UsuarioDao.VerificarCPFEEmail(usuario)){
            return UsuarioDao.salvarUsuario(usuario);
        }else{
            System.out.println("Usuario com email ou cpf já registrado");
            return false;
        }
    }
    
    public List<UsuarioModel> AparecerUsuario(UsuarioModel usuario){
        return UsuarioDao.AparecerUsuarios();
        
    }
    
   public boolean atualizarUsuario(UsuarioModel usuario, String cpf){
      return UsuarioDao.atualizarUsuario( usuario, cpf);
   }
    
public boolean removerUsuario(String cpf){
    boolean isAdm = UsuarioDao.usuarioIsAdm(cpf);

    if (!isAdm) {
        return false;  // Impede a remoção se não for admin
    }

    return UsuarioDao.removerUsuario(cpf);
}

    
 public String loginUsuario(UsuarioModel usuario) throws Exception {
    // Valida as credenciais do usuário chamando o método DAO
    boolean usuarioValido = UsuarioDao.loginUsuario(usuario);

    if (!usuarioValido) {
        throw new Exception("Credenciais inválidas"); // Exceção lançada se o login falhar
    }

    // Gerar o JWT se as credenciais estiverem corretas
    String jwtToken = Jwts.builder()
                       .setSubject(usuario.getCpf())
                       .setIssuedAt(new Date())
                       .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                                               .atZone(ZoneId.of("America/Sao_Paulo"))
                                               .toInstant()))
                       .signWith(CHAVE, SignatureAlgorithm.HS512)
                       .compact();


    return jwtToken;
}

    
//agr essa porra deu certo, graças a Deus
public boolean promoverUsuario(String cpf, boolean is_adm){
    
    if(UsuarioDao.usuarioIsAdm(cpf)){
        System.out.println("Usuario ja é adm");
    }
    if(!UsuarioDao.usuarioIsAdm(cpf)){
        System.out.println("Usuario não é adm");
    }
    
    
    //testar dps
   if(UsuarioDao.usuarioIsAdm(cpf)){
    boolean sucesso = UsuarioDao.promoverUsuario(cpf, is_adm);
    if(sucesso){
        System.out.println("Usuário promovido com sucesso.");
    }
   }else {
        System.out.println("Falha ao promover o usuário.");
        return false;
    }
    return false;
}

}
