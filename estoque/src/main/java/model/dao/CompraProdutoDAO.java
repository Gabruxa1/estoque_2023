package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Compra;
import model.bean.CompraProduto;


public class CompraProdutoDAO {
    public void create(CompraProduto cp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO compra_produto(id_compra, id_produto, quantidade, valor_unitario_compra) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cp.getCompra().getId());
            stmt.setInt(2, cp.getProduto().getId());
            stmt.setInt(3, cp.getQuantidade());
            stmt.setDouble(4, cp.getValorUnitarioCompra());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto adicionado à Compra com sucesso");
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Produto na Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<CompraProduto> read(Compra c) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<CompraProduto> produtosCompra = new ArrayList();
        
        try {
            String sql = "SELECT * FROM compra_produto WHERE id_compra = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                CompraProduto cp = new CompraProduto();
                cp.setCompra(c);
                cp.setProduto(new ProdutoDAO().find(rs.getInt("id_produto")));
                cp.setQuantidade(rs.getInt("quantidade"));
                cp.setValorUnitarioCompra(rs.getDouble("valor_unitario_compra"));
                
                produtosCompra.add(cp);
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Produtos da Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return produtosCompra;
    }
    
    public CompraProduto find(int idCompra, int idProduto) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CompraProduto cp = null;
        
        try {
            String sql = "SELECT * FROM compra_produto WHERE id_compra = ? and id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCompra);
            stmt.setInt(2, idProduto);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                cp = new CompraProduto();
                cp.setCompra(new CompraDAO().find(rs.getInt("id_compra")));
                cp.setProduto(new ProdutoDAO().find(rs.getInt("id_produto")));
                cp.setQuantidade(rs.getInt("quantidade"));
                cp.setValorUnitarioCompra(rs.getDouble("valor_unitario_compra"));
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Produto da Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return cp;
    }
    
    public void update(CompraProduto cp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE compra_produto SET quantidade = ?, valor_unitario_compra = ? WHERE id_compra = ? and id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cp.getQuantidade());
            stmt.setDouble(2, cp.getValorUnitarioCompra());
            stmt.setInt(3, cp.getCompra().getId());
            stmt.setInt(4, cp.getProduto().getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto da COmpra Atualizado com sucesso");
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao Atualizar Produto da Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void delete(CompraProduto cp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM compra_produto WHERE id_compra = ? and id_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cp.getCompra().getId());
            stmt.setInt(2, cp.getProduto().getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluído da Compra com sucesso");
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Produto da Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}