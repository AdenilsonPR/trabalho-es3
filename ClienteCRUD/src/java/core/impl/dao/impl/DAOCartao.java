package core.impl.dao.impl;

import dominio.Cartao;
import dominio.EntidadeDominio;
import dominio.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class DAOCartao extends DAOAbstract {

    @Override
    public void salvar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Cartao cartao = (Cartao) entidade;

        try {

            StringBuilder sqlCartao = new StringBuilder();
            sqlCartao.append("INSERT INTO tb_cartao(car_status, car_bandeira, car_cli_id, car_numero, car_nomeimpresso, car_codseg, car_validade, car_preferencial)");
            sqlCartao.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            pst = conexao.prepareStatement(sqlCartao.toString());
            pst.setString(1, cartao.getEtdStatus());
            pst.setString(2, cartao.getCarBandeira());
            pst.setInt(3, cartao.getCarCliId());
            pst.setString(4, cartao.getCarNumero());
            pst.setString(5, cartao.getCarNomeImpresso());
            pst.setString(6, cartao.getCarCodSeguranca());
            pst.setString(7, cartao.getCarValidade());
            pst.setString(8, cartao.getCarIsPreferencial());
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
    public void alterar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Cartao cartao = (Cartao) entidade;

        try {

            // Persiste Cartao.
            StringBuilder sqlCartao = new StringBuilder();
            sqlCartao.append("UPDATE tb_cartao SET car_bandeira=?, car_numero=?, car_nomeimpresso=?, car_codseg=?, car_validade=?, car_preferencial=?");
            sqlCartao.append("WHERE car_id = ?;");

            pst = conexao.prepareStatement(sqlCartao.toString());
            pst.setString(1, cartao.getCarBandeira());
            pst.setString(2, cartao.getCarNumero());
            pst.setString(3, cartao.getCarNomeImpresso());
            pst.setString(4, cartao.getCarCodSeguranca());
            pst.setString(5, cartao.getCarValidade());
            pst.setString(6, cartao.getCarIsPreferencial());
            pst.setInt(7, cartao.getEtdId());
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
        openConnection();
        PreparedStatement pst = null;
        Cartao cartao = (Cartao) entidade;

        try {

            // Persiste Cartao.
            StringBuilder sqlCartao = new StringBuilder();
            sqlCartao.append("UPDATE tb_cartao SET car_status=?, car_preferencial=?");
            sqlCartao.append(" WHERE car_id = ?;");

            pst = conexao.prepareStatement(sqlCartao.toString());
            pst.setString(1, "desativo");
            pst.setString(2, "Não");
            pst.setInt(3, cartao.getEtdId());
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
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        Cartao cartao = (Cartao) entidade;
        List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();

        String sql = "SELECT * FROM tb_cartao WHERE car_cli_id=? and car_status ='ativo' and car_id<>?;";

        try {
            openConnection();
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, cartao.getCarCliId());
            pst.setInt(2, cartao.getEtdId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Cartao c = new Cartao();
                c.setEtdId(rs.getInt("car_id"));
                c.setEtdStatus(rs.getString("car_status"));
                c.setCarBandeira(rs.getString("car_bandeira"));
                c.setCarCliId(rs.getInt("car_cli_id"));
                c.setCarCodSeguranca(rs.getString("car_codseg"));
                c.setCarIsPreferencial(rs.getString("car_preferencial"));
                c.setCarNomeImpresso(rs.getString("car_nomeimpresso"));
                c.setCarNumero(rs.getString("car_numero"));
                c.setCarValidade(rs.getString("car_validade"));
                cartoes.add(c);
            }

            return cartoes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
