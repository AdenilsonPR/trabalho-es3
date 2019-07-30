package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOEndereco;
import core.impl.negocio.IStrategy;
import dominio.Endereco;
import dominio.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

public class VerificaEnderecoCobrancaExcluir implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        Endereco endereco = (Endereco) entidade;

        IDAO dao = new DAOEndereco();
        List enderecos = dao.consultar(endereco);

        List<Endereco> enderecosCli = new ArrayList<>();

        for (int i = 0; i < enderecos.size(); i++) {
            Endereco enderecoAux = (Endereco) enderecos.get(i);
            if (enderecoAux.getEndIsCobranca().equals("Não")) {
                enderecosCli.add(enderecoAux);
            }
        }

        if (enderecosCli.size() > 0) {
            if (endereco.getEndIsCobranca().equals("Sim")) {
                enderecosCli.get(0).setEndIsCobranca("Sim");
                dao.alterar(enderecosCli.get(0));
            }
        }

        return null;
    }

}
