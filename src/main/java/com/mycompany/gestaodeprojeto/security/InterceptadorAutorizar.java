
package com.mycompany.gestaodeprojeto.security;


import com.mycompany.gestaodeprojeto.security.Autorizar;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

@Provider
@Autorizar
//@Priority(Priorities.AUTHENTICATION)
public class InterceptadorAutorizar implements ContainerRequestFilter{

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "essachavevaiseramaisdificildomundoeniguemalemdeyannvaiacertartmj"
                    .getBytes(StandardCharsets.UTF_8));
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            Jws claim = Jwts.parserBuilder().setSigningKey(CHAVE).build().parseClaimsJws(token);
            System.out.println(claim.getBody().toString());
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException  | NullPointerException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
}
