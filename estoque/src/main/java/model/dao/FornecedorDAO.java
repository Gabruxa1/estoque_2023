package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}

