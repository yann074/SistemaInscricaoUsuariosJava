package com.mycompany.gestaodeprojeto;

import com.mycompany.gestaodeprojeto.DAO.ConnDao;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api/v1")
public class JakartaRestConfiguration extends Application {

    public JakartaRestConfiguration() {
        System.out.println("Teste");
    }

}
