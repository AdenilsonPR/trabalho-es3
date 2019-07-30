package core.impl.negocio.impl;

import core.impl.negocio.IStrategy;
import dominio.EntidadeDominio;
import java.util.Date;

public class ComplementarDtCad implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade != null) {
            Date data = new Date();
            entidade.setEtdDataCadastro(data);
            return null;
        }
        return "Erro ao gerar data de cadastro ";
    }
}
