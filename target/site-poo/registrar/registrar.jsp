<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <title>Cadastrar</title>
</head>

<body>
<div class="container">
    <h1>Cadastrar Usuário</h1>
    <form action="${pageContext.request.contextPath}/registrar" method="POST">
        <div class="form-group">
            <label for="nome">Usuário</label>
            <input type="text" name="nome" class="form-control" id="nome" required placeholder="Insira seu nome de usuário">
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
            <label for="tipoUsuario">Escolha seu tipo de usuário:</label>
            <select name="tipoUsuario" id="tipoUsuario" class="form-control" required>
                <option value="">Nenhum</option>
                <option value="aluno">Aluno</option>
                <option value="professor">Professor</option>
            </select>
        </div>
        <button type="submit" name="opcao" class="btn btn-primary" value="Cadastrar">Cadastrar Usuário</button>
        <a href="../index.jsp" class="btn btn-back">Voltar</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</body>

</html>