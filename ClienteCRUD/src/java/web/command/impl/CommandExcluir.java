package web.command.impl;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;

public class CommandExcluir extends CommandAbstract {

    @Override
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.excluir(entidade);
    }

}
