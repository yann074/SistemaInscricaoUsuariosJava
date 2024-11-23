package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.MiniCursoDAO;
import com.mycompany.gestaodeprojeto.Models.MiniCursoModel;
import java.sql.Timestamp;
import java.util.List;

public class MiniCursoController {
    private MiniCursoDAO miniCursoDAO;

    public MiniCursoController() {
        this.miniCursoDAO = new MiniCursoDAO();
    }

    public boolean salvarMiniCurso(MiniCursoModel miniCurso) {
        if (miniCurso != null) {
            return miniCursoDAO.salvarMiniCurso(miniCurso);
        } else {
            System.out.println("MiniCurso inválido");
            return false;
        }
    }

    public List<MiniCursoModel> listarMiniCurso() {
        return miniCursoDAO.listarMiniCurso();
    }

    public boolean removerMiniCurso(int id, Timestamp data_alt) {
        if (id > 0) {
            return miniCursoDAO.removerMiniCurso(id, data_alt);
        } else {
            System.out.println("ID inválido");
            return false;
        }
    }

    public boolean atualizarMiniCurso(MiniCursoModel miniCurso, int id) {
        if (miniCurso != null && id > 0) {
            return miniCursoDAO.atualizarMiniCurso(miniCurso, id);
        } else {
            System.out.println("Dados inválidos para atualização");
            return false;
        }
    }
}
