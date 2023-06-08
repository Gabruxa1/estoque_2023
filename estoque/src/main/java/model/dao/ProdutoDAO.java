package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;


public class ProdutoDAO {
    public void create(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO produto (descricao, valor_unitario, quantidade,id_tipo_produto) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUnitario());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getTipoProduto().getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao inserir Produto");
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Produto> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList();
        
        try {
            String sql = "SELECT * FROM produto";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorUnitario(rs.getDouble("valor_unitario"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setTipoProduto(new TipoProdutoDAO().find(rs.getInt("id_tipo_produto")));
                
                produtos.add(p);
            }
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Produtos");
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return produtos;
    }
    
        public Produto find(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;
        
        try {
            String sql = "SELECT * FROM produto WHERE id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setDescricao(rs.getString("valor_unitario"));
                p.setDescricao(rs.getString("quantidade"));
                p.setTipoProduto(new TipoProdutoDAO().find(rs.getInt("id_tipo_produto")));
             
            }
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Produtos");
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return p;
    }
    
    public void update(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE produto SET descricao = ?, valor_unitario = ?, quantidade = ?, id_tipo_produto = ? WHERE id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUnitario());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getTipoProduto().getId());
            stmt.setInt(5, p.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar produto. Erro: " + ex);
        }
    }
    
    public void delete(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir produto. Erro: " + ex);
        }
    }
}
