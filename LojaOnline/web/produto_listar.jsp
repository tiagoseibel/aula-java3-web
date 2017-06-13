<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Produtos</title>
        <script type="text/javascript">
            function excluir(formProduto) {
                var opt = confirm('Are you sure?');
                if (opt) {
                    formProduto.form_action.value = 'excluir';
                    formProduto.submit();
                }
            }
        </script>
    </head>
    <body>
        <form action="/lojaonline/produto" method="POST" name="formProduto">
            Código: <input type="text" name="codigo" value="${prd.codigo}">
            <br />
            Nome: <input type="text" name="nome" value="${prd.nome}">
            <br>
            Preço: <input type="text" name="preco" value="${prd.preco}">
            <br>
            Desconto: <input type="text" name="desconto" value="${prd.desconto}">
            <br>
            Qtd. em estoque: <input type="text" name="qtd_estoque" value="${prd.qtdEstoque}">
            <br>

            <c:if test="${prd.codigo > 0}">
                <input type="hidden" name="form_action" value="editar" />
            </c:if>

            <c:if test="${prd == null}">
                <input type="hidden" name="form_action" value="inserir" />
            </c:if>

            <input type="submit" value="Gravar">            
            <input type="reset" value="Cancelar">
            <input type="button" value="Excluir" onclick="excluir(this.form);">
        </form>
        <p></p>
        <table border="1">
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Desconto</th>
                    <th>Qtd. Estoque</th>
                    <th>-</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${produtos}" var="item">
                    <tr>
                        <td>${item.codigo}</td>
                        <td>${item.nome}</td>
                        <td>${item.preco}</td>
                        <td>${item.desconto}</td>
                        <td>${item.qtdEstoque}</td>
                        <td><a href="/lojaonline/produto?codigo=${item.codigo}">Selecionar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
