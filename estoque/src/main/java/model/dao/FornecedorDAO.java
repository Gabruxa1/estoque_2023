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


public class FornecedorDAO {
    public void create(Fornecedor f) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO fornecedor(cnpj, razao_social,email,telefone)" + "VALUES (?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getCnpj());
            stmt.setString(2, f.getRazaoSocial());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor adastrado com sucesso");
            
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
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao consultar");
           
        } finally{
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
    public void update(Fornecedor f){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String sql = "UPDATE fornecedor " + 
                        "SET cnpj = ?, razao_social = ?, email = ?, telefone = ? "+
                        "WHERE id_fornecedor = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getCnpj());
            stmt.setString(2, f.getRazaoSocial());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            stmt.setInt(5, f.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Fornecedor atualizado com sucesso!");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao atualizar fornecedor. Erro: " + ex);
        }
    }
    
    public void delete(Fornecedor f){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Fornecedor excluído com sucesso!");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao excluir fornecedor. Erro: " + ex);
        }
    }
}