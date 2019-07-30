package core.impl.negocio.impl;

import core.impl.negocio.IStrategy;
import dominio.Cliente;
import dominio.EntidadeDominio;

public class ValidarCliente implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        if (cliente.getCliCPF() == null || cliente.getCliCPF().trim().equals("")) {
            return "CPF invalido! ";
        }

        if (cliente.getPesDataNascimento() == null || cliente.getPesDataNascimento().trim().equals("")) {
            return "Data de nascimento invalida! ";
        }
        
        if (cliente.getPesNome() == null || cliente.getPesNome().trim().equals("")) {
            return "Nome invalida! ";
        }

        return null;
    }

}
