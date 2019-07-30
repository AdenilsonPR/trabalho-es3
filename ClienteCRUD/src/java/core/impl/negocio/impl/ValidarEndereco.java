package core.impl.negocio.impl;

import core.impl.negocio.IStrategy;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;

public class ValidarEndereco implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        String nomeClasse = entidade.getClass().getSimpleName();

        if (nomeClasse.equals("Endereco")) {
            Endereco endereco = (Endereco) entidade;

            if (endereco.getEndBairro() == null || endereco.getEndBairro().equals("")) {
                return "Bairro invalido! ";
            }

            if (endereco.getEndCEP() == null || endereco.getEndCEP().equals("") || endereco.getEndCEP().length() != 9) {
                return "CEP invalido! ";
            }

            if (endereco.getEndCidade() == null || endereco.getEndCidade().equals("")) {
                return "Cidade invalido! ";
            }

            if (endereco.getEndEstado() == null || endereco.getEndEstado().equals("")) {
                return "Estado invalido! ";
            }

            if (endereco.getEndLogradouro() == null || endereco.getEndLogradouro().equals("")) {
                return "Logradouro invalido! ";
            }

            if (endereco.getEndNumero() == null || endereco.getEndNumero().equals("")) {
                return "Numero endereco invalido! ";
            }
        } else {

            Cliente cliente = (Cliente) entidade;

            for (int i = 0; i < cliente.getPesEndereco().size(); i++) {
                if (cliente.getPesEndereco().get(i).getEndBairro() == null || cliente.getPesEndereco().get(i).getEndBairro().equals("")) {
                    return "Bairro invalido! ";
                }

                if (cliente.getPesEndereco().get(i).getEndCEP() == null || cliente.getPesEndereco().get(i).getEndCEP().equals("") || cliente.getPesEndereco().get(i).getEndCEP().length() != 9) {
                    return "CEP invalido! ";
                }

                if (cliente.getPesEndereco().get(i).getEndCidade() == null || cliente.getPesEndereco().get(i).getEndCidade().equals("")) {
                    return "Cidade invalido! ";
                }

                if (cliente.getPesEndereco().get(i).getEndEstado() == null || cliente.getPesEndereco().get(i).getEndEstado().equals("")) {
                    return "Estado invalido! ";
                }

                if (cliente.getPesEndereco().get(i).getEndLogradouro() == null || cliente.getPesEndereco().get(i).getEndLogradouro().equals("")) {
                    return "Logradouro invalido! ";
                }

                if (cliente.getPesEndereco().get(i).getEndNumero() == null || cliente.getPesEndereco().get(i).getEndNumero().equals("")) {
                    return "Numero endereco invalido! ";
                }
            }
        }
        return null;
    }

}
