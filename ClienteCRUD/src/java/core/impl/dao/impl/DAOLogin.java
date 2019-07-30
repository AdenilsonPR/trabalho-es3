package core.impl.dao.impl;

import core.util.Criptografar;
import dominio.EntidadeDominio;
import dominio.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLogin extends DAOAbstract {

    @Override
    public void salvar(EntidadeDominio entidade) {
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Login login = (Login) entidade;

        try {

            // Persiste Cartao.
            StringBuilder sqlLogin = new StringBuilder();
            sqlLogin.append("UPDATE tb_login SET lgn_senha=?");
            sqlLogin.append("WHERE lgn_id=?;");

            pst = null;
            pst = conexao.prepareStatement(sqlLogin.toString());
            pst.setString(1, Criptografar.criaCriptografia(login.getLgnSenhaNova()));
            pst.setInt(2, login.getEtdId());
            pst.executeUpdate();

        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        Login login = (Login) entidade;
        List<EntidadeDominio> logins = new ArrayList<EntidadeDominio>();

        String sql = "SELECT * FROM tb_login WHERE lgn_id = ?;";

        try {
            openConnection();
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, login.getEtdId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Login l = new Login();
                l.setEtdStatus(rs.getString("lgn_status"));
                l.setLgnEmail(rs.getString("lgn_email"));
                l.setLgnSenhaAntida(rs.getString("lgn_senha"));
                logins.add(l);
            }

            return logins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
