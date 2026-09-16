package br.com.uniube.todo.controller;

import br.com.uniube.todo.model.Tarefa;
import br.com.uniube.todo.service.TarefaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tarefas")
public class TarefaServlet extends HttpServlet {

    private static final String LISTA = "/WEB-INF/views/lista.jsp";
    private static final String FORM = "/WEB-INF/views/form.jsp";

    private final TarefaService tarefaService = new TarefaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {
            case "novo":
                req.setAttribute("tarefa", null);
                req.getRequestDispatcher(FORM).forward(req, resp);
                break;

            case "editar":
                int idEditar = Integer.parseInt(req.getParameter("id"));
                Tarefa tarefa = tarefaService.buscarPorId(idEditar);
                req.setAttribute("tarefa", tarefa);
                req.getRequestDispatcher(FORM).forward(req, resp);
                break;

            case "excluir":
                int idExcluir = Integer.parseInt(req.getParameter("id"));
                tarefaService.deletar(idExcluir);
                resp.sendRedirect(req.getContextPath() + "/tarefas");
                break;

            case "concluir":
                int idConcluir = Integer.parseInt(req.getParameter("id"));
                tarefaService.alternarConcluida(idConcluir);
                resp.sendRedirect(req.getContextPath() + "/tarefas");
                break;

            default:
                req.setAttribute("tarefas", tarefaService.listar());
                req.getRequestDispatcher(LISTA).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // Monta o objeto Tarefa a partir dos campos do formulário
        Tarefa tarefa = new Tarefa();
        String idTexto = req.getParameter("id");
        if (idTexto != null && !idTexto.isBlank()) {
            tarefa.setId(Integer.parseInt(idTexto));
        }
        tarefa.setTitulo(req.getParameter("titulo"));
        tarefa.setDescricao(req.getParameter("descricao"));

        try {
            tarefaService.salvar(tarefa);
            resp.sendRedirect(req.getContextPath() + "/tarefas");
        } catch (IllegalArgumentException e) {
            // Erro de validação: volta pro formulário mostrando a mensagem
            req.setAttribute("erro", e.getMessage());
            req.setAttribute("tarefa", tarefa);
            req.getRequestDispatcher(FORM).forward(req, resp);
        }
    }
}