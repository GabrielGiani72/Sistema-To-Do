<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Minhas Tarefas</title>
</head>
<body>

<h1>Minhas Tarefas</h1>

<a href="${pageContext.request.contextPath}/tarefas?acao=novo">+ Nova Tarefa</a>

<c:if test="${empty tarefas}">
    <p>Nenhuma tarefa cadastrada ainda.</p>
</c:if>

<table border="1" cellpadding="8">
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
                <c:if test="${tarefa.concluida}">Sim</c:if>
                <c:if test="${!tarefa.concluida}">Nao</c:if>
            </td>
            <td>${tarefa.dataCriacao}</td>
            <td>
                <a href="${pageContext.request.contextPath}/tarefas?acao=editar&id=${tarefa.id}">Editar</a>
                |
                <a href="${pageContext.request.contextPath}/tarefas?acao=concluir&id=${tarefa.id}">Concluir/Desmarcar</a>
                |
                <a href="${pageContext.request.contextPath}/tarefas?acao=excluir&id=${tarefa.id}">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
