package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {
    private Connection con;
    public ProdutoDAO() throws DAOException {
        try {
            con = ConnectionUtil.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DAOException("Erro ao conectar ao Banco de Dados", ex);
        }
    }
    
    public void insert(Produto produto) 
    throws DAOException
    {
        String sql = "insert into produto (codigo,nome,preco,desconto,qtd_estoque) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCodigo() );
            ps.setString(2, produto.getNome() );
            ps.setDouble(3, produto.getPreco() );
            ps.setDouble(4, produto.getDesconto() );
            ps.setInt(5, produto.getQtdEstoque() );
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir produto!", ex);
        }
    }
    
    public void editar(Produto produto) 
    throws DAOException
    {
        String sql = "update produto set nome=?, preco=?, "
                   + "desconto=?, qtd_estoque=? "
                   + "where codigo = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getNome() );
            ps.setDouble(2, produto.getPreco() );
            ps.setDouble(3, produto.getDesconto() );
            ps.setInt(4, produto.getQtdEstoque() );
            ps.setInt(5, produto.getCodigo() );
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new DAOException("Erro ao editar produto!", ex);
        }
    }
    
    public void excluir(int id)
    throws DAOException
    {
        String sql = "delete from produto where codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir produto!", ex);
        }
    }
    
    public List<Produto> listarTodos()
    throws DAOException 
    {
        List<Produto> lista = new ArrayList<>();
        String sql = "select * from produto";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next() ) {
               Produto p = new Produto();
               p.setCodigo( rs.getInt("codigo") );
               p.setNome( rs.getString("nome") );
               p.setPreco( rs.getDouble("preco") );
               p.setQtdEstoque( rs.getInt("qtd_estoque") );
               p.setDesconto( rs.getDouble("desconto") );
               lista.add(p);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DAOException("Erro ao executar listarTodos", ex);
        }
        return lista;
    }
    
    public Produto buscarPorCodigo(int codigo)
    throws DAOException 
    {
        Produto p = new Produto();
        String sql = "select * from produto where codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next() ) {
               p.setCodigo( rs.getInt("codigo") );
               p.setNome( rs.getString("nome") );
               p.setPreco( rs.getDouble("preco") );
               p.setQtdEstoque( rs.getInt("qtd_estoque") );
               p.setDesconto( rs.getDouble("desconto") );
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DAOException("Erro ao executar buscarPorCodigo", ex);
        }
        return p;
    }    
    
    public static void main(String[] args) {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.listarTodos();
        } catch (DAOException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
