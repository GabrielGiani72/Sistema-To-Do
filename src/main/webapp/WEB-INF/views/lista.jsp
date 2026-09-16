<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Minhas Tarefas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>Minhas Tarefas</h1>

    <a class="btn-nova" href="${pageContext.request.contextPath}/tarefas?acao=novo">+ Nova Tarefa</a>

    <c:if test="${empty tarefas}">
        <p>Nenhuma tarefa cadastrada ainda.</p>
    </c:if>

    <c:if test="${not empty tarefas}">
        <table>
            <tr>
                <th>Titulo</th>
                <th>Descricao</th>
                <th>Concluida</th>
                <th>Data</th>
                <th>Acoes</th>
            </tr>

            <c:forEach var="tarefa" items="${tarefas}">
                <tr>
                    <td>${tarefa.titulo}</td>
                    <td>${tarefa.descricao}</td>
                    <td>
                        <c:if test="${tarefa.concluida}"><span class="sim">Sim</span></c:if>
                        <c:if test="${!tarefa.concluida}"><span class="nao">Nao</span></c:if>
                    </td>
                    <td>${tarefa.dataCriacao}</td>
                    <td class="acoes">
                        <a href="${pageContext.request.contextPath}/tarefas?acao=editar&id=${tarefa.id}">Editar</a>
                        <a href="${pageContext.request.contextPath}/tarefas?acao=concluir&id=${tarefa.id}">Concluir/Desmarcar</a>
                        <a href="${pageContext.request.contextPath}/tarefas?acao=excluir&id=${tarefa.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>

</body>
</html>
