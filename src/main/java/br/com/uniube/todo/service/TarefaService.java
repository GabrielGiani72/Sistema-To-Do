package br.com.uniube.todo.service;

import br.com.uniube.todo.dao.TarefaDAO;
import br.com.uniube.todo.model.Tarefa;

import java.time.LocalDate;
import java.util.List;

public class TarefaService {

    private static final int TITULO_MAXIMO = 150;

    private final TarefaDAO tarefaDAO;

    public TarefaService() {
        this.tarefaDAO = new TarefaDAO();
    }

    public List<Tarefa> listar() {
        return this.tarefaDAO.listarTodas();
    }

    public Tarefa buscarPorId(Integer id) {
        if (id == null) {
            return null;
        }
        return this.tarefaDAO.buscarPorId(id);
    }

    //Regras de salvamento

    public void salvar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa é obrigatória.");
        }

        tarefa.setTitulo(this.normalizar(tarefa.getTitulo()));
        this.validarTitulo(tarefa.getTitulo());

        if (tarefa.getId() == 0) {
            tarefa.setDataCriacao(LocalDate.now());
            this.tarefaDAO.inserir(tarefa);
            return;
        }

        if (this.tarefaDAO.buscarPorId(tarefa.getId()) == null) {
            throw new IllegalArgumentException("Tarefa não encontrada para alteração.");
        }
        this.tarefaDAO.alterar(tarefa);
    }

    //Regras de exclusão

    public void deletar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id é obrigatório para excluir.");
        }
        if (this.tarefaDAO.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Tarefa não encontrada.");
        }
        this.tarefaDAO.deletar(id);
    }

    //Alterna o status de concluída/pendente de uma tarefa

    public void alternarConcluida(Integer id) {
        Tarefa tarefa = this.buscarPorId(id);
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa não encontrada.");
        }
        tarefa.setConcluida(!tarefa.isConcluida());
        this.tarefaDAO.alterar(tarefa);
    }

    private void validarTitulo(String titulo) {
        if (titulo == null) {
            throw new IllegalArgumentException("O título da tarefa é obrigatório.");
        }
        if (titulo.length() > TITULO_MAXIMO) {
            throw new IllegalArgumentException(
                    "O título deve ter no máximo " + TITULO_MAXIMO + " caracteres.");
        }
    }

    private String normalizar(String valor) {
        if (valor == null) {
            return null;
        }
        String limpo = valor.trim();
        return limpo.isEmpty() ? null : limpo;
    }
}