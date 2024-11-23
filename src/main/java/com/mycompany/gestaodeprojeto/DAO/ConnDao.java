/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yanns
 */
public class ConnDao {
        
    
    private static String url = "jdbc:mysql://localhost:3306/GestaodeEventoss";
    private static String root = "root";
    private static String password = "";
    
    public static Connection conn(){
        
        Connection connection = null;
      try{
           connection = DriverManager.getConnection(url, root, password);
           System.out.println("conectado com sucesso");
      } catch(SQLException e){
          System.out.println("naao conseguiu ser salvo");
      }
      return connection;
    }
}
