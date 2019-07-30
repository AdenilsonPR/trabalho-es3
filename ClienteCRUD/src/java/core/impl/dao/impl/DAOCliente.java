package core.impl.dao.impl;

import core.util.Criptografar;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Login;
import dominio.Telefone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAOCliente extends DAOAbstract {

    private PreparedStatement pst;

    @Override
    public void salvar(EntidadeDominio entidade) {

        Cliente cliente = (Cliente) entidade;

        openConnection();

        try {

            // Persiste Cliente.
            StringBuilder sqlCliente = new StringBuilder();
            sqlCliente.append("INSERT INTO tb_cliente(cli_datacadastro, cli_status, cli_nome, cli_datanascimento, cli_genero, cli_cpf, cli_rank)");
            sqlCliente.append("VALUES (?, ?, ?, ?, ?, ?, ?);");

            pst = conexao.prepareStatement(sqlCliente.toString());
            pst.setTimestamp(1, new Timestamp(cliente.getEtdDataCadastro().getTime()));
            pst.setString(2, cliente.getEtdStatus());
            pst.setString(3, cliente.getPesNome());
            pst.setString(4, cliente.getPesDataNascimento());
            pst.setString(5, cliente.getPesGenero());
            pst.setString(6, cliente.getCliCPF());
            pst.setInt(7, cliente.getCliRank());
            pst.executeUpdate();

            // Persiste Endereco.
            StringBuilder sqlEndereco = new StringBuilder();
            sqlEndereco.append("INSERT INTO tb_endereco(end_status, end_tipo, end_logradouro, end_tipologradouro, end_numero, end_bairro, end_cep, end_cidade, end_estado, end_pais, end_cobranca, end_entrega, end_cli_id)");
            sqlEndereco.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            for (int i = 0; i < cliente.getPesEndereco().size(); i++) {
                pst = null;
                pst = conexao.prepareStatement(sqlEndereco.toString());
                pst.setString(1, cliente.getPesEndereco().get(i).getEtdStatus());
                pst.setString(2, cliente.getPesEndereco().get(i).getEndTipo());
                pst.setString(3, cliente.getPesEndereco().get(i).getEndLogradouro());
                pst.setString(4, cliente.getPesEndereco().get(i).getEndTipoLogradouro());
                pst.setString(5, cliente.getPesEndereco().get(i).getEndNumero());
                pst.setString(6, cliente.getPesEndereco().get(i).getEndBairro());
                pst.setString(7, cliente.getPesEndereco().get(i).getEndCEP());
                pst.setString(8, cliente.getPesEndereco().get(i).getEndCidade());
                pst.setString(9, cliente.getPesEndereco().get(i).getEndEstado());
                pst.setString(10, cliente.getPesEndereco().get(i).getEndPais());
                pst.setString(11, cliente.getPesEndereco().get(i).getEndIsCobranca());
                pst.setString(12, cliente.getPesEndereco().get(i).getEndIsEntrega());
                pst.setInt(13, ultimoIdPersistido(new Cliente()));
                pst.executeUpdate();
            }

            // Persiste Login.
            StringBuilder sqlLogin = new StringBuilder();
            sqlLogin.append("INSERT INTO tb_login(lgn_status, lgn_email, lgn_senha, lgn_cli_id)");
            sqlLogin.append("VALUES (?, ?, ?, ?);");

            pst = null;
            pst = conexao.prepareStatement(sqlLogin.toString());
            pst.setString(1, cliente.getPesLogin().getEtdStatus());
            pst.setString(2, cliente.getPesLogin().getLgnEmail());
            pst.setString(3, Criptografar.criaCriptografia(cliente.getPesLogin().getLgnSenhaNova()));
            pst.setInt(4, ultimoIdPersistido(new Cliente()));
            pst.executeUpdate();

            // Persiste Telefone.
            StringBuilder sqlTelefone = new StringBuilder();
            sqlTelefone.append("INSERT INTO tb_telefone(tel_status, tel_tipo, tel_ddd, tel_numero, tel_cli_id)");
            sqlTelefone.append("VALUES (?, ?, ?, ?, ?);");

            pst = null;
            pst = conexao.prepareStatement(sqlTelefone.toString());
            pst.setString(1, cliente.getPesTelefone().getEtdStatus());
            pst.setString(2, cliente.getPesTelefone().getTelTipo());
            pst.setString(3, cliente.getPesTelefone().getTelDDD());
            pst.setString(4, cliente.getPesTelefone().getTelNumero());
            pst.setInt(5, ultimoIdPersistido(new Cliente()));
            pst.executeUpdate();

            // Persiste Cartao.
            StringBuilder sqlCartao = new StringBuilder();
            sqlCartao.append("INSERT INTO tb_cartao(car_status, car_bandeira, car_cli_id, car_numero, car_nomeimpresso, car_codseg, car_validade, car_preferencial)");
            sqlCartao.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            for (int i = 0; i < cliente.getCliCartao().size(); i++) {
                pst = null;
                pst = conexao.prepareStatement(sqlCartao.toString());
                pst.setString(1, cliente.getCliCartao().get(i).getEtdStatus());
                pst.setString(2, cliente.getCliCartao().get(i).getCarBandeira());
                pst.setInt(3, ultimoIdPersistido(new Cliente()));
                pst.setString(4, cliente.getCliCartao().get(i).getCarNumero());
                pst.setString(5, cliente.getCliCartao().get(i).getCarNomeImpresso());
                pst.setString(6, cliente.getCliCartao().get(i).getCarCodSeguranca());
                pst.setString(7, cliente.getCliCartao().get(i).getCarValidade());
                pst.setString(8, cliente.getCliCartao().get(i).getCarIsPreferencial());
                pst.executeUpdate();
            }
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
        Cliente cliente = (Cliente) entidade;

        try {

            // Persiste Cliente.
            StringBuilder sqlCliente = new StringBuilder();
            sqlCliente.append("UPDATE tb_cliente SET cli_nome=?, cli_datanascimento=?, cli_genero=?, cli_cpf=?, cli_rank=?");
            sqlCliente.append("WHERE cli_id=?;");

            pst = conexao.prepareStatement(sqlCliente.toString());
            pst.setString(1, cliente.getPesNome());
            pst.setString(2, cliente.getPesDataNascimento());
            pst.setString(3, cliente.getPesGenero());
            pst.setString(4, cliente.getCliCPF());
            pst.setInt(5, cliente.getCliRank());
            pst.setInt(6, cliente.getEtdId());
            pst.executeUpdate();

            StringBuilder sqlTelefone = new StringBuilder();
            sqlTelefone.append("UPDATE tb_telefone SET tel_tipo=?, tel_ddd=?, tel_numero=?");
            sqlTelefone.append("WHERE tel_cli_id = ?;");

            pst = null;
            pst = conexao.prepareStatement(sqlTelefone.toString());
            pst.setString(1, cliente.getPesTelefone().getTelTipo());
            pst.setString(2, cliente.getPesTelefone().getTelDDD());
            pst.setString(3, cliente.getPesTelefone().getTelNumero());
            pst.setInt(4, cliente.getEtdId());
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
        Cliente cliente = (Cliente) entidade;

        try {

            // Persiste Cliente.
            StringBuilder sqlCliente = new StringBuilder();
            sqlCliente.append("UPDATE tb_cliente SET cli_status=?");
            sqlCliente.append("WHERE cli_id=?;");

            pst = conexao.prepareStatement(sqlCliente.toString());
            pst.setString(1, "desativo");
            pst.setInt(2, cliente.getEtdId());
            pst.executeUpdate();

            // Persiste Endereco.
            StringBuilder sqlEndereco = new StringBuilder();
            sqlEndereco.append("UPDATE tb_endereco SET end_status=?");
            sqlEndereco.append("WHERE end_cli_id=?");

            pst = null;
            pst = conexao.prepareStatement(sqlEndereco.toString());
            pst.setString(1, "desativo");
            pst.setInt(2, cliente.getEtdId());
            pst.executeUpdate();

            // Persiste Login.
            StringBuilder sqlLogin = new StringBuilder();
            sqlLogin.append("UPDATE tb_login SET lgn_status=?");
            sqlLogin.append("WHERE lgn_cli_id=?;");

            pst = null;
            pst = conexao.prepareStatement(sqlLogin.toString());
            pst.setString(1, "desativo");
            pst.setInt(2, cliente.getEtdId());
            pst.executeUpdate();

            // Persiste Telefone.
            StringBuilder sqlTelefone = new StringBuilder();
            sqlTelefone.append("UPDATE tb_telefone SET tel_status=?");
            sqlTelefone.append("WHERE tel_cli_id = ?;");

            pst = null;
            pst = conexao.prepareStatement(sqlTelefone.toString());
            pst.setString(1, "desativo");
            pst.setInt(2, cliente.getEtdId());
            pst.executeUpdate();

            // Persiste Cartao.
            StringBuilder sqlCartao = new StringBuilder();
            sqlCartao.append("UPDATE tb_cartao SET car_status=?");
            sqlCartao.append("WHERE car_cli_id = ?;");

            pst = null;
            pst = conexao.prepareStatement(sqlCartao.toString());
            pst.setString(1, "desativo");
            pst.setInt(2, cliente.getEtdId());
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

        Cliente cliente = (Cliente) entidade;
        List<Cartao> cartoes = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();

        PreparedStatement pstCliente = null;
        PreparedStatement pstTelefone = null;
        PreparedStatement pstEndereco = null;
        PreparedStatement pstLogin = null;
        PreparedStatement pstCartao = null;

        String sqlCliente = null;

        if (cliente.getCliPesquisa() != null) {
            sqlCliente = "Select * from tb_cliente WHERE "
                    + "CAST(cli_id as text) LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "CAST(cli_datacadastro as text) LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "cli_status LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "cli_nome LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "cli_datanascimento LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "cli_genero LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "cli_cpf LIKE '%" + cliente.getCliPesquisa() + "%' or "
                    + "CAST(cli_rank as text) LIKE '%" + cliente.getCliPesquisa() + "%' ORDER BY cli_id;";
        } else {
            sqlCliente = "SELECT * FROM tb_cliente ORDER BY cli_id;";
        }

        String sqlTelefone = "SELECT * FROM tb_telefone WHERE tel_cli_id = ? ORDER BY tel_id;";
        String sqlEndereco = "SELECT * FROM tb_endereco WHERE end_cli_id = ? ORDER BY end_id;";
        String sqlLogin = "SELECT * FROM tb_login WHERE lgn_cli_id = ?;";
        String sqlCartao = "SELECT * FROM tb_cartao WHERE car_cli_id = ? ORDER BY car_id;";

        try {
            openConnection();
            pstCliente = conexao.prepareStatement(sqlCliente);
            ResultSet rsCliente = pstCliente.executeQuery();

            List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();

            while (rsCliente.next()) {

                Cliente c = new Cliente();
                c.setEtdId(rsCliente.getInt("cli_id"));
                c.setEtdDataCadastro(rsCliente.getDate("cli_datacadastro"));
                c.setEtdStatus(rsCliente.getString("cli_status"));
                c.setPesNome(rsCliente.getString("cli_nome"));
                c.setPesDataNascimento(rsCliente.getString("cli_datanascimento"));
                c.setPesGenero(rsCliente.getString("cli_genero"));
                c.setCliCPF(rsCliente.getString("cli_cpf"));
                c.setCliRank(rsCliente.getInt("cli_rank"));

                pstTelefone = conexao.prepareStatement(sqlTelefone);
                pstTelefone.setInt(1, c.getEtdId());
                ResultSet rsTelefone = pstTelefone.executeQuery();

                while (rsTelefone.next()) {
                    Telefone telefone = new Telefone();
                    telefone.setEtdId(rsTelefone.getInt("tel_id"));
                    telefone.setEtdStatus(rsTelefone.getString("tel_status"));
                    telefone.setTelTipo(rsTelefone.getString("tel_tipo"));
                    telefone.setTelDDD(rsTelefone.getString("tel_ddd"));
                    telefone.setTelNumero(rsTelefone.getString("tel_numero"));

                    c.setPesTelefone(telefone);
                }

                pstEndereco = conexao.prepareStatement(sqlEndereco);
                pstEndereco.setInt(1, c.getEtdId());
                ResultSet rsEndereco = pstEndereco.executeQuery();

                while (rsEndereco.next()) {

                    Endereco endereco = new Endereco();
                    endereco.setEtdId(rsEndereco.getInt("end_id"));
                    endereco.setEtdStatus(rsEndereco.getString("end_status"));
                    endereco.setEndTipo(rsEndereco.getString("end_tipo"));
                    endereco.setEndLogradouro(rsEndereco.getString("end_logradouro"));
                    endereco.setEndTipoLogradouro(rsEndereco.getString("end_tipologradouro"));
                    endereco.setEndNumero(rsEndereco.getString("end_numero"));
                    endereco.setEndBairro(rsEndereco.getString("end_bairro"));
                    endereco.setEndCEP(rsEndereco.getString("end_cep"));
                    endereco.setEndCidade(rsEndereco.getString("end_cidade"));
                    endereco.setEndEstado(rsEndereco.getString("end_estado"));
                    endereco.setEndPais(rsEndereco.getString("end_pais"));
                    endereco.setEndIsCobranca(rsEndereco.getString("end_cobranca"));
                    endereco.setEndIsEntrega(rsEndereco.getString("end_entrega"));
                    endereco.setEndCliId(rsEndereco.getInt("end_cli_id"));
                    enderecos.add(endereco);
                    c.setPesEndereco(enderecos);
                }

                pstLogin = conexao.prepareStatement(sqlLogin);
                pstLogin.setInt(1, c.getEtdId());
                ResultSet rsLogin = pstLogin.executeQuery();

                while (rsLogin.next()) {

                    Login login = new Login();
                    login.setEtdId(rsLogin.getInt("lgn_id"));
                    login.setEtdStatus(rsLogin.getString("lgn_status"));
                    login.setLgnEmail(rsLogin.getString("lgn_email"));
                    login.setLgnSenhaAntida(rsLogin.getString("lgn_senha"));
                    c.setPesLogin(login);
                }

                pstCartao = conexao.prepareStatement(sqlCartao);
                pstCartao.setInt(1, c.getEtdId());
                ResultSet rsCartao = pstCartao.executeQuery();

                while (rsCartao.next()) {

                    Cartao cartao = new Cartao();
                    cartao.setEtdId(rsCartao.getInt("car_id"));
                    cartao.setEtdStatus(rsCartao.getString("car_status"));
                    cartao.setCarBandeira(rsCartao.getString("car_bandeira"));
                    cartao.setCarNumero(rsCartao.getString("car_numero"));
                    cartao.setCarNomeImpresso(rsCartao.getString("car_nomeimpresso"));
                    cartao.setCarCodSeguranca(rsCartao.getString("car_codseg"));
                    cartao.setCarValidade(rsCartao.getString("car_validade"));
                    cartao.setCarIsPreferencial(rsCartao.getString("car_preferencial"));
                    cartao.setCarCliId(rsCartao.getInt("car_cli_id"));
                    cartoes.add(cartao);
                    c.setCliCartao(cartoes);
                }

                clientes.add(c);
            }

            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
