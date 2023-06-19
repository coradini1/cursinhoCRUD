<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    .content-section {
      margin-top: 20px;
    }

    .btn-group {
      margin-top: 10px;
    }
  </style>
  <title>Listar Materiais</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Menu</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/listarcursos">Listar Cursos</a>
      </li>
    </ul>
    <span class="navbar-text">
            Logado como <strong><%= session.getAttribute("username") %></strong>
        </span>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger ml-3">Sair</a>
  </div>
</nav>

<div class="container content-section">
  <h1>Listar Materiais</h1>

  <table class="table">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Descrição</th>
      <th>Arquivo</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${materiais}" var="material">
      <tr>
        <td>${material.id}</td>
        <td>${material.nome}</td>
        <td>${material.descricao}</td>
        <td>${material.nomeArquivo}</td>
        <td>
          <div class="btn-group">
            <a href="${pageContext.request.contextPath}/editar-material?id=${material.id}" class="btn btn-primary">Editar</a>
            <a href="${pageContext.request.contextPath}/remover-material?id=${material.id}" class="btn btn-danger">Remover</a>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
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
