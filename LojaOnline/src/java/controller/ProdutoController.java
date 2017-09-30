package controller;

import dao.DAOException;
import dao.ProdutoDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Instancia a DAO
            ProdutoDAO dao = new ProdutoDAO();
            // Obtem lista de Produtos
            List<Produto> produtos = dao.listarTodos();
            // Adiciona atributo (produtos) na Request
            request.setAttribute("produtos", produtos);

            // Se receber um codigo
            String codigo = request.getParameter("codigo");
            if (codigo != null && !codigo.equals("")) {
                // Pesquisa por codigo
                Produto produto
                        = dao.buscarPorCodigo(Integer.parseInt(codigo));
                // Envia o atributo para o JSP
                request.setAttribute("prd", produto);
                
            }

            // Envia para o JSP
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher("/produto_listar.jsp");
            dispatcher.forward(request, response);

        } catch (DAOException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage());
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Produto produto = new Produto();
        produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        produto.setNome(request.getParameter("nome"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setDesconto(Double.parseDouble(request.getParameter("desconto")));
        produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtd_estoque")));

        String formAction = request.getParameter("form_action");

        try {
            ProdutoDAO dao = new ProdutoDAO();

            if (formAction.equals("inserir")) {
                dao.inserir(produto);
            } else if (formAction.equals("editar")) {
                dao.editar(produto);
            } else if (formAction.equals("excluir")) {
                dao.excluir(produto.getCodigo());
            }

            List<Produto> produtos = dao.listarTodos();
            request.setAttribute("produtos", produtos);

            if (formAction.equals("inserir")) {
                response.sendRedirect("/lojaonline/produto");
            } else {
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/produto_listar.jsp");
                dispatcher.forward(request, response);
            }

        } catch (DAOException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage());
        }

    }

}
