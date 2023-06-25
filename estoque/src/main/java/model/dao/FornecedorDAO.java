/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.bean.Fornecedor;

/**
 *
 * @author Aluno
 */
public class FornecedorDAO {
    public void create(Fornecedor f) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO fornecedor(cnpj,razao_social,email,telefone) "
                    + "VALUES (?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getCnpj());
            stmt.setString(2, f.getRazaoSocial());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Fornecedor> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> fornecedores = new ArrayList();
        
        try {
            String sql = "SELECT * FROM fornecedor";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmail(rs.getString("email"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setTelefone(rs.getString("telefone"));
                
                fornecedores.add(f);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar fornecedores");
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
    public Fornecedor find(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Fornecedor f = null;
        
        try {
            String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                f = new Fornecedor();
                
                f.setId(rs.getInt("id_fornecedor"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Fornecedores. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return f;
    }
    
    public void update(Fornecedor f) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE fornecedor "
                    + "SET cnpj = ?, razao_social = ?, email = ?, telefone = ? "
                    + "WHERE id_fornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getCnpj());
            stmt.setString(2, f.getRazaoSocial());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            stmt.setInt(5, f.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar fornecedor. Erro: " + ex);
        }
    }
    
    public void delete(Fornecedor f) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir fornecedor. Erro: " + ex);
        }
    }
}
