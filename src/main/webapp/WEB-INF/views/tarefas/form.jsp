<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tarefa</title>
</head>
<body>

<c:choose>
    <c:when test="${empty tarefa}">
        <h1>Nova Tarefa</h1>
    </c:when>
    <c:otherwise>
        <h1>Editar Tarefa</h1>
    </c:otherwise>
</c:choose>

<c:if test="${not empty erro}">
    <p style="color: red;">${erro}</p>
</c:if>

<form action="${pageContext.request.contextPath}/tarefas" method="post">

    <c:if test="${not empty tarefa}">
        <input type="hidden" name="id" value="${tarefa.id}">
    </c:if>

    <label>Titulo:</label><br>
    <input type="text" name="titulo" value="${tarefa.titulo}"><br><br>

    <label>Descricao:</label><br>
    <textarea name="descricao" rows="4" cols="40">${tarefa.descricao}</textarea><br><br>

    <button type="submit">Salvar</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/tarefas">Voltar para a lista</a>

</body>
</html>
