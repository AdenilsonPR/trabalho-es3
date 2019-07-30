package web.command.impl;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;

public class CommandAlterar extends CommandAbstract {

    @Override
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.alterar(entidade);
    }

}
