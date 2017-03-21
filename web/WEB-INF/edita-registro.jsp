
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edita Reclamações</title>
    </head>
    <body>
        <h1>Edita Reclamações</h1>
        <form method="post">
            <div><input type="hidden" name="id" value="${pessoa.id}"/>id: ${pessoa.id}</div>
            <div><label>Nome: <input type="text" name="nome" value="${pessoa.nome}"</label></div>
            <div><label>Email: <input type="text" name="email" value="${pessoa.email}"</label></div>
            <div><label>Descricao: <input type="text" name="descricao" value="${pessoa.descricao}"</label></div>
            <div><label>Status: <input type="text" name="status" value="${pessoa.status}"</label></div>
            
            <div><input type="submit"></div>
        </form>
    </body>
</html>