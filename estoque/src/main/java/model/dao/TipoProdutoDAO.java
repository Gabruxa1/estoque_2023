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
import model.bean.TipoProduto;


public class TipoProdutoDAO {
    public void create(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO tipo_produto (descricao) VALUES (?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tp.getDescricao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(TipoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<TipoProduto> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TipoProduto> tiposProduto = new ArrayList();
        
        try {
            String sql = "SELECT * FROM tipo_produto";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                TipoProduto tp = new TipoProduto();
                tp.setId(rs.getInt("id_tipo_produto"));
                tp.setDescricao(rs.getString("descricao"));
                
                tiposProduto.add(tp);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Tipos de Produto");
            Logger.getLogger(TipoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return tiposProduto;
    }
    
        public TipoProduto find(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoProduto tp = null;
        
        try {
            String sql = "SELECT * FROM tipo_produto WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                tp = new TipoProduto();
                tp.setId(rs.getInt("id_tipo_produto"));
                tp.setDescricao(rs.getString("descricao"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Tipos de Produto");
            Logger.getLogger(TipoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return tp;
    }
    
    
    public void update(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE tipo_produto SET descricao = ? WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tp.getDescricao());
            stmt.setInt(2, tp.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tipo de Produto atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Tipo de Produto. Erro: " + ex);
        }
    }
    
    public void delete(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM tipo_produto WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, tp.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tipo de Produto excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Tipo de Produto. Erro: " + ex);
        }
    }
}
