<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Erros</title>
    </head>
    <body>
        <h1><%=exception.getMessage() %></h1>
    </body>
</html>
