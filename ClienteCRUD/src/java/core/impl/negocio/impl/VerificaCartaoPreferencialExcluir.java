package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOCartao;
import core.impl.negocio.IStrategy;
import dominio.Cartao;
import dominio.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

public class VerificaCartaoPreferencialExcluir implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        Cartao cartao = (Cartao) entidade;

        IDAO dao = new DAOCartao();
        List cartoes = dao.consultar(cartao);

        List<Cartao> cartoesCli = new ArrayList<>();

        for (int i = 0; i < cartoes.size(); i++) {
            Cartao cartaoAux = (Cartao) cartoes.get(i);
            if (cartaoAux.getCarIsPreferencial().equals("Não")) {
                cartoesCli.add(cartaoAux);
            }
        }
        if (cartoesCli.size() > 0) {
            cartoesCli.get(0).setCarIsPreferencial("Sim");
            dao.alterar(cartoesCli.get(0));
        }

        return null;
    }

}
