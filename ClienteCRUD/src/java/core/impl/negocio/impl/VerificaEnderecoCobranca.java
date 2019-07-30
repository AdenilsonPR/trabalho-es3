package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOEndereco;
import core.impl.negocio.IStrategy;
import dominio.Cartao;
import dominio.Endereco;
import dominio.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

public class VerificaEnderecoCobranca implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        Endereco endereco = (Endereco) entidade;

        IDAO dao = new DAOEndereco();
        List enderecos = dao.consultar(endereco);

        List<Endereco> enderecosCli = new ArrayList<>();

        for (int i = 0; i < enderecos.size(); i++) {
            Endereco enderecoAux = (Endereco) enderecos.get(i);
            if (enderecoAux.getEndIsCobranca().equals("Sim")) {
                enderecosCli.add(enderecoAux);
            }
        }

        if (enderecosCli.size() > 0) {
            if (endereco.getEndIsCobranca().equals("Sim") && endereco.getEndIsEntrega().equals("Não") || endereco.getEndIsCobranca().equals("Sim") && endereco.getEndIsEntrega().equals("Sim")) {
                enderecosCli.get(0).setEndIsCobranca("Não");
                dao.alterar(enderecosCli.get(0));
            }
        } else if (!endereco.getEndIsCobranca().equals("Sim")) {
            return "É obrigatorio um endereço de cobrança! ";
        }

        return null;
    }

}
