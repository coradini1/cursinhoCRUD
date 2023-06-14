<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
    <link rel="stylesheet" href="">
    <title>Cadastrar</title>
</head>

<body>
<div>
    <div class="container">
        <h1>Cadastrar Usuario</h1>
        <form action="servlets">
            <div class="form-floating mb-3">
                <label for="nome">usuario</label>
                <input type="text" name="nome" class="form-control" id="nome" required placeholder="Insira seu nome de usuario">
            </div>
            <div class="form-floating mb-3">
                <label for="senha">Senha</label>
                <input type="password" name="senha" id="senha" class="form-control" required placeholder="Insira sua senha">
                </div>
            <div class="form-floating mb-3">
                <label for="idade">Idade</label>
                <input type="number" name="idade" id="idade" class="form-control" max="105" required placeholder="Insira sua idade">
            </div>
            <div class="form-floating mb-3">
                <label for="tipoUsuario">Escolha seu tipo de usuario:</label>
                <select name="tipoUsuario" id="tipoUsuario" class="form-select" aria-label="Default select example" required>
                    <option value="">Nenhum</option>
                    <option value="1">Aluno</option>
                    <option value="2">Professor</option>
                </select>
                </div>
            <button type="submit" name="opcao" class="btn btn-primary" value="Cadastrar">Cadastrar Usuario</button>
            <a href="../index.jsp" class="btn btn-primary">Voltar</a>
        </form>
        <br>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>