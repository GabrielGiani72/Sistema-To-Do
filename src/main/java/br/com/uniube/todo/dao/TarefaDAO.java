package br.com.uniube.todo.dao;

import br.com.uniube.todo.config.ConexaoBD;
import br.com.uniube.todo.model.Tarefa;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TarefaDAO {

    private final ConexaoBD banco;

    public TarefaDAO() {
        this.banco = ConexaoBD.getInstance();
    }

    //Busca todas as tarefas cadastradas
    public List<Tarefa> listarTodas() {
        String sql = "SELECT id, titulo, descricao, concluida, data_criacao "
                + "FROM tarefas ORDER BY data_criacao DESC, id DESC";

        List<Tarefa> lista = new ArrayList<>();
        try (ResultSet rs = this.banco.executar(sql)) {
            while (rs.next()) {
                lista.add(this.mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tarefas.", e);
        }
        return lista;
    }

    //Busca uma única tarefa pelo id. Retorna null se não existir.
    public Tarefa buscarPorId(int id) {
        String sql = "SELECT id, titulo, descricao, concluida, data_criacao "
                + "FROM tarefas WHERE id = ?";

        try (ResultSet rs = this.banco.executar(sql, id)) {
            if (rs.next()) {
                return this.mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tarefa por id.", e);
        }
        return null;
    }

    //Insere uma nova tarefa no banco
    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, concluida, data_criacao) "
                + "VALUES (?, ?, ?, ?)";

        try {
            this.banco.executarUpdate(
                    sql,
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    tarefa.isConcluida(),
                    Date.valueOf(tarefa.getDataCriacao()));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tarefa.", e);
        }
    }

    //Atualiza os dados de uma tarefa já existente (identificada pelo id)
    public void alterar(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, concluida = ? "
                + "WHERE id = ?";

        try {
            this.banco.executarUpdate(
                    sql,
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    tarefa.isConcluida(),
                    tarefa.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar tarefa.", e);
        }
    }

    //Remove uma tarefa do banco pelo id
    public void deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try {
            this.banco.executarUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tarefa.", e);
        }
    }

    private Tarefa mapear(ResultSet rs) throws SQLException {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(rs.getInt("id"));
        tarefa.setTitulo(rs.getString("titulo"));
        tarefa.setDescricao(rs.getString("descricao"));
        tarefa.setConcluida(rs.getBoolean("concluida"));
        tarefa.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
        return tarefa;
    }
}