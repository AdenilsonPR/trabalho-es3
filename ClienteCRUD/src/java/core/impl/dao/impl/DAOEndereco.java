package core.impl.dao.impl;

import dominio.Endereco;
import dominio.EntidadeDominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEndereco extends DAOAbstract {

    @Override
    public void salvar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Endereco endereco = (Endereco) entidade;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_endereco(end_status, end_tipo, end_logradouro, end_tipologradouro, end_numero, end_bairro, end_cep, end_cidade, end_estado, end_pais, end_cobranca, end_entrega, end_cli_id)");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, endereco.getEtdStatus());
            pst.setString(2, endereco.getEndTipo());
            pst.setString(3, endereco.getEndLogradouro());
            pst.setString(4, endereco.getEndTipoLogradouro());
            pst.setString(5, endereco.getEndNumero());
            pst.setString(6, endereco.getEndBairro());
            pst.setString(7, endereco.getEndCEP());
            pst.setString(8, endereco.getEndCidade());
            pst.setString(9, endereco.getEndEstado());
            pst.setString(10, endereco.getEndPais());
            pst.setString(11, endereco.getEndIsCobranca());
            pst.setString(12, endereco.getEndIsEntrega());
            pst.setInt(13, endereco.getEndCliId());
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
        Endereco endereco = (Endereco) entidade;

        try {

            // Persiste Cartao.
            StringBuilder sqlEndereco = new StringBuilder();
            sqlEndereco.append("UPDATE tb_endereco SET end_tipo=?, end_logradouro=?, end_tipologradouro=?, end_numero=?, end_bairro=?, end_cep=?, end_cidade=?, end_estado=?, end_pais=?, end_cobranca=?, end_entrega=?");
            sqlEndereco.append("WHERE end_id=?");

            pst = null;
            pst = conexao.prepareStatement(sqlEndereco.toString());
            pst.setString(1, endereco.getEndTipo());
            pst.setString(2, endereco.getEndLogradouro());
            pst.setString(3, endereco.getEndTipoLogradouro());
            pst.setString(4, endereco.getEndNumero());
            pst.setString(5, endereco.getEndBairro());
            pst.setString(6, endereco.getEndCEP());
            pst.setString(7, endereco.getEndCidade());
            pst.setString(8, endereco.getEndEstado());
            pst.setString(9, endereco.getEndPais());
            pst.setString(10, endereco.getEndIsCobranca());
            pst.setString(11, endereco.getEndIsEntrega());
            pst.setInt(12, endereco.getEtdId());
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
        Endereco endereco = (Endereco) entidade;

        try {

            // Persiste Cartao.
            StringBuilder sqlEndereco = new StringBuilder();
            sqlEndereco.append("UPDATE tb_endereco SET end_status=?, end_cobranca=?, end_entrega=?");
            sqlEndereco.append("WHERE end_id=?");

            pst = null;
            pst = conexao.prepareStatement(sqlEndereco.toString());
            pst.setString(1, "desativo");
            pst.setString(2, "Não");
            pst.setString(3, "Não");
            pst.setInt(4, endereco.getEtdId());
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

        Endereco endereco = (Endereco) entidade;
        List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();

        String sql = "SELECT * FROM tb_endereco WHERE end_cli_id=? and end_status ='ativo' and end_id<>?;";

        try {
            openConnection();
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, endereco.getEndCliId());
            pst.setInt(2, endereco.getEtdId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Endereco e = new Endereco();
                e.setEtdId(rs.getInt("end_id"));
                e.setEtdStatus(rs.getString("end_status"));
                e.setEndTipo(rs.getString("end_tipo"));
                e.setEndIsCobranca(rs.getString("end_cobranca"));
                e.setEndIsEntrega(rs.getString("end_entrega"));
                e.setEndLogradouro(rs.getString("end_logradouro"));
                e.setEndTipoLogradouro(rs.getString("end_tipologradouro"));
                e.setEndNumero(rs.getString("end_numero"));
                e.setEndBairro(rs.getString("end_bairro"));
                e.setEndCEP(rs.getString("end_cep"));
                e.setEndCidade(rs.getString("end_cidade"));
                e.setEndEstado(rs.getString("end_estado"));
                e.setEndPais(rs.getString("end_pais"));
                e.setEndCliId(rs.getInt("end_cli_id"));

                enderecos.add(e);
            }

            return enderecos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
