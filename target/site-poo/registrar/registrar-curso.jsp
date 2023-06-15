<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
    response.sendRedirect("block.jsp");
    return;
  }
%>
<html>
<head>
    <title>Title</title>
</head><!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <title>Registrar Curso</title>
</head>

<body>
<div class="container">
  <h1>Registrar Curso</h1>
  <form action="${pageContext.request.contextPath}/registrar-curso" method="POST">
    <div class="form-group">
      <label for="nome">Nome do Curso</label>
      <input type="text" name="nome" class="form-control" id="nome" required>
    </div>
    <div class="form-group">
      <label for="descricao">Descrição do Curso</label>
      <textarea name="descricao" id="descricao" class="form-control" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Registrar Curso</button>
    <a href="../index.jsp" class="btn btn-primary">Voltar</a>
  </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>

<body>

</body>
</html>
