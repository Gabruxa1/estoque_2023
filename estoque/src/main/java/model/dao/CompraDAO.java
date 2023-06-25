package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Compra;


public class CompraDAO {
    public void create(Compra c) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO compra(data, id_fornecedor, id_usuario) VALUES (now(),?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getFornecedor().getId());
            stmt.setInt(2, c.getUsuario().getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao inserir Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Compra> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Compra> compras = new ArrayList();
        
        try {
            String sql = "SELECT * FROM compra";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Compra c = new Compra();
                FornecedorDAO daoFornecedor = new FornecedorDAO();
                UsuarioDAO daoUsuario = new UsuarioDAO();
                
                c.setId(rs.getInt("id_compra"));
                c.setData(rs.getTimestamp("data"));
                c.setFornecedor(daoFornecedor.find(rs.getInt("id_fornecedor")));
                c.setUsuario(daoUsuario.find(rs.getInt("id_usuario")));
                
                compras.add(c);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Compras. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return compras;
    }
    
    public Compra find(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compra c = null;
        
        try {
            String sql = "SELECT * FROM compra WHERE id_compra = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                c = new Compra();
                FornecedorDAO daoFornecedor = new FornecedorDAO();
                UsuarioDAO daoUsuario = new UsuarioDAO();
                
                c.setId(rs.getInt("id_compra"));
                c.setData(rs.getTimestamp("data"));
                c.setFornecedor(daoFornecedor.find(rs.getInt("id_fornecedor")));
                c.setUsuario(daoUsuario.find(rs.getInt("id_usuario")));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao encontrar Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return c;
    }
    
    public void update(Compra c) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE compra SET id_fornecedor = ?, id_usuario = ? WHERE id_compra = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getFornecedor().getId());
            stmt.setInt(2, c.getUsuario().getId());
            stmt.setInt(3, c.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void delete(Compra c) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM compra WHERE id_compra = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Compra. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
