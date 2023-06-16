<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Login</title>
</head>

<body>
<div class="container">
    <h1>Login</h1>
    <form action="login" method="POST">
        <div class="form-group">
            <label for="username">Usuario</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="Insira o seu nome de usuario">
        </div>
        <div class="form-group">
            <label for="password">Senha</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Insira sua senha">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Entrar</button>
            <a href="registrar/registrar.jsp" class="btn btn-primary btn-register">Registrar</a>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</body>

</html>
