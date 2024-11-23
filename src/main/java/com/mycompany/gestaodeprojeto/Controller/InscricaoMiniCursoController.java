/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.InscricaoMiniCursoDAO;
import com.mycompany.gestaodeprojeto.Models.InscricaoMiniCursoModel;
import com.mycompany.gestaodeprojeto.Models.MiniCursoModel;
import com.mycompany.gestaodeprojeto.Models.UsuarioModel;
import java.util.List;

/**
 *
 * @author yanns
 */
public class InscricaoMiniCursoController {
    
    private InscricaoMiniCursoDAO inscricaoMiniCursoDAO;

    public InscricaoMiniCursoController() {
        this.inscricaoMiniCursoDAO = new InscricaoMiniCursoDAO();
    }
    
    public boolean salvarInscricao(InscricaoMiniCursoModel inscritoMiniCurso, int id_miniCurso, int id_evento){
        return inscricaoMiniCursoDAO.salvarInscricao(inscritoMiniCurso, id_miniCurso, id_evento);
               
    }
    
    public boolean removerInscricaoMiniCurso(InscricaoMiniCursoModel inscritoMiniCurso, String cpf_usuario){
        return inscricaoMiniCursoDAO.removerInscricaoEvento(inscritoMiniCurso, cpf_usuario);
    }
    
      public List<UsuarioModel> listarInscritosNoMiniCurso(int id_minicurso){
        return inscricaoMiniCursoDAO.listarInscritosNoMiniCurso(id_minicurso);
    }
    
}
