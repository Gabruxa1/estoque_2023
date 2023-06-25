/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author grego
 */
public class UsuarioDAO {
    public ArrayList<Usuario> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Usuario> usuarios = new ArrayList();
        
        try {
            String sql = "SELECT * FROM usuario";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id_usuario"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                
                usuarios.add(u);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Usuários. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return usuarios;
    }
    
    public Usuario find(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = null;
        
        try {
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                u = new Usuario();
                
                u.setId(rs.getInt("id_usuario"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao encontrar Usuário. Erro: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return u;
    }
}
