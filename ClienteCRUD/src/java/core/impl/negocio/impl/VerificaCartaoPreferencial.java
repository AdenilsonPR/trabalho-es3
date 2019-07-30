package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOCartao;
import core.impl.negocio.IStrategy;
import dominio.Cartao;
import dominio.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

public class VerificaCartaoPreferencial implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        Cartao cartao = (Cartao) entidade;

        IDAO dao = new DAOCartao();
        List cartoes = dao.consultar(cartao);

        List<Cartao> cartoesCli = new ArrayList<>();

        for (int i = 0; i < cartoes.size(); i++) {
            Cartao cartaoAux = (Cartao) cartoes.get(i);
            if (cartaoAux.getCarIsPreferencial().equals("Sim")) {
                cartoesCli.add(cartaoAux);
            }
        }

        if (cartoesCli.size() > 0) {
            if (cartao.getCarIsPreferencial().equals("Sim")) {
                cartoesCli.get(0).setCarIsPreferencial("Não");
                dao.alterar(cartoesCli.get(0));
            }
        } else if (!cartao.getCarIsPreferencial().equals("Sim")) {
            return "É obrigatorio um cartão preferencial! ";
        }

        return null;
    }

}
