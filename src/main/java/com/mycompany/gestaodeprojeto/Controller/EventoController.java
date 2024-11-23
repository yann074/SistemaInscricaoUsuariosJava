package com.mycompany.gestaodeprojeto.Controller;

import com.mycompany.gestaodeprojeto.DAO.EventoDAO;
import com.mycompany.gestaodeprojeto.Models.EventoModel;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EventoController {
    private EventoDAO eventoDAO;

    public EventoController() {
        this.eventoDAO = new EventoDAO();
    }

    public boolean salvarEvento(EventoModel evento) {
        if (evento != null) {
            return eventoDAO.salvarEvento(evento);
        } else {
            System.out.println("Evento inválido");
            return false;
        }
    }

    public List<EventoModel> listarEventos(String dataInicio, String dataFim) {
        return eventoDAO.listarEvento(dataInicio,dataFim);
    }

    public boolean removerEvento(int id) {
        if (id > 0) {
            return eventoDAO.removerEvento(id);
        } else {
            System.out.println("ID inválido");
            return false;
        }
    }

    public boolean atualizarEvento(EventoModel evento, int id, Timestamp data_alt) {
        if (evento != null && id > 0) {
            return eventoDAO.atualizarEvento(evento, id, data_alt);
        } else {
            System.out.println("Dados inválidos para atualização");
            return false;
        }
    }

    public List<EventoModel> listarProgramacaoEvento(int id) {
        if (id > 0) {
            return eventoDAO.listarProgramacaoEvento(id);
        } else {
            System.out.println("ID inválido");
            return null;
        }
    }
}
