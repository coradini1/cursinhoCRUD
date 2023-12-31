<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
        response.sendRedirect("block.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        .navbar-text strong {
            color: white;
        }
    </style>
    <title>Edit Course</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/listarcursos">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/listarcursos">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/registrar/registrar-curso.jsp"><strong>Registrar Curso</a>
            </li>
        </ul>
        <span class="navbar-text">
            Logado como <strong><%= session.getAttribute("username") %></strong>
        </span>
        <a href="logout.jsp" class="btn btn-danger ml-3">Logout</a>
    </div>
</nav>

<div class="container">
    <h1>Editar Curso</h1>
    <form action="${pageContext.request.contextPath}/atualizar-curso" method="POST">
        <input type="hidden" name="id" value="${curso.id}">
        <div class="form-group">
            <label>Nome do curso:</label>
            <input type="text" name="nome" class="form-control" required value="${curso.nome}">
        </div>
        <div class="form-group">
            <label>Descrição do curso:</label>
            <textarea name="descricao" required class="form-control">${curso.descricao}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Atualizar</button>
        <a href="${pageContext.request.contextPath}/listarcursos" class="btn btn-secondary">Voltar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
