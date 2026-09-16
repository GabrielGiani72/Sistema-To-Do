<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tarefa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <c:choose>
        <c:when test="${empty tarefa}">
            <h1>Nova Tarefa</h1>
        </c:when>
        <c:otherwise>
            <h1>Editar Tarefa</h1>
        </c:otherwise>
    </c:choose>

    <c:if test="${not empty erro}">
        <p class="erro">${erro}</p>
    </c:if>

    <div class="form-card">
        <form action="${pageContext.request.contextPath}/tarefas" method="post">

            <c:if test="${not empty tarefa}">
                <input type="hidden" name="id" value="${tarefa.id}">
            </c:if>

            <label>Titulo:</label>
            <input type="text" name="titulo" value="${tarefa.titulo}">

            <label>Descricao:</label>
            <textarea name="descricao" rows="4">${tarefa.descricao}</textarea>

            <button type="submit">Salvar</button>
        </form>
    </div>

    <a class="voltar" href="${pageContext.request.contextPath}/tarefas">Voltar para a lista</a>

</div>

</body>
</html>
