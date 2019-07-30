package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOCliente;
import core.impl.negocio.IStrategy;
import dominio.Cliente;
import dominio.EntidadeDominio;
import java.util.List;

public class ValidarExistencia implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        Cliente cliente = (Cliente) entidade;
        IDAO dao = new DAOCliente();

        cliente.setCliPesquisa(cliente.getCliCPF());
        List resultado = dao.consultar(cliente);

        if (resultado.size() > 0) {
            List<Cliente> cli = (List<Cliente>) resultado;
            for (int i = 0; i < cli.size(); i++) {
                if (cli.get(i).getEtdStatus().equals("ativo")) {
                    return "Cliente já cadastrado! ";
                }
            }
        }

        cliente.setCliPesquisa("");
        return null;
    }
}
