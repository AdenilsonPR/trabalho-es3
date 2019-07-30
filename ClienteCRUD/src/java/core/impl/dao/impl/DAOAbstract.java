package core.impl.dao.impl;

import core.impl.dao.IDAO;
import core.util.Conexao;
import dominio.EntidadeDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAOAbstract implements IDAO {

    protected Connection conexao;

    protected void openConnection() {
        try {

            if (conexao == null || conexao.isClosed()) {
                conexao = Conexao.getConnection();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int ultimoIdPersistido(EntidadeDominio entidade) {
        int id = 0;
        String nomeTabela = "tb_" + entidade.getClass().getSimpleName().toLowerCase();
        String nomeColunaId = null;

        if (nomeTabela.equals("tb_cartao")) {
            nomeColunaId = "car_id";
        } else if (nomeTabela.equals("tb_cliente")) {
            nomeColunaId = "cli_id";
        } else if (nomeTabela.equals("tb_endereco")) {
            nomeColunaId = "end_id";
        } else if (nomeTabela.equals("tb_login")) {
            nomeColunaId = "lgn_id";
        } else if (nomeTabela.equals("tb_telefone")) {
            nomeColunaId = "tel_id";
        }

        try {
            String sql = "SELECT MAX (" + nomeColunaId + ") FROM " + nomeTabela +";";
            PreparedStatement pst;
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("max");
            }

            return id;
        } catch (Exception e) {
            return 0;
        }
    }
}
