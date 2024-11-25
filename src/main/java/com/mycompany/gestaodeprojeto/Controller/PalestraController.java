package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.PalestraDAO;
import com.mycompany.gestaodeprojeto.Models.PalestraModel;
import java.util.Date;
import java.util.List;

public class PalestraController {

    private PalestraDAO palestraDAO;

    public PalestraController() {
        this.palestraDAO = new PalestraDAO();
    }

    public boolean salvarPalestra(PalestraModel palestra) {
        if (palestra == null) {
            System.out.println("A palestra fornecida é inválida");
            return false;
        }

        return palestraDAO.salvarPalestra(palestra);
    }

    public List<PalestraModel> listarPalestras() {
        return palestraDAO.listarPalestra();
    }

    public boolean removerPalestra(int id) {
        if (id <= 0) {
            System.out.println("ID inválido");
            return false;
        }
        return palestraDAO.removerPalestra(id);
    }

    public boolean atualizarPalestra(PalestraModel palestra, int id, Date data_alt) {
        if (palestra == null || id <= 0) {
            System.out.println("Palestra ou ID inválido");
            return false;
        }
        return palestraDAO.atualizarPalestra(palestra, id, data_alt);
    }
}
