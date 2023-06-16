<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Cadastrar</title>
</head>

<body>
<div class="container">
    <h1>Cadastrar Usuario</h1>
    <form action="${pageContext.request.contextPath}/registrarUser" method="POST">
        <div class="form-group">
            <label for="nome">Usuario</label>
            <input type="text" name="nome" class="form-control" id="nome" required placeholder="Insira seu nome de usuario">
        </div>
        <div class="form-group">
            <label for="senha">Senha</label>
            <input type="password" name="senha" id="senha" class="form-control" required placeholder="Insira sua senha">
        </div>
        <div class="form-group">
            <label for="idade">Idade</label>
            <input type="number" name="idade" id="idade" class="form-control" max="105" required placeholder="Insira sua idade">
        </div>
        <div class="form-group">
            <label for="tipoUsuario">Escolha seu tipo de usuario:</label>
            <select name="tipoUsuario" id="tipoUsuario" class="form-control" required>
                <option value="">Nenhum</option>
                <option value="aluno">Aluno</option>
                <option value="professor">Professor</option>
            </select>
        </div>
        <button type="submit" name="opcao" class="btn btn-primary" value="Cadastrar">Cadastrar Usuario</button>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-back">Voltar</a>
    </form>
</div>
<script>
    <% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
    window.onload = function() {
        alert("<%= errorMessage %>");
    }
    <% } %>
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</body>

</html>
