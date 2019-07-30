package core.impl.negocio.impl;

import core.impl.negocio.IStrategy;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Telefone;

public class ValidarTelefone implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        String nomeClasse = entidade.getClass().getSimpleName();

        if (nomeClasse.equals("Telefone")) {
            Telefone telefone = (Telefone) entidade;

            if (telefone.getTelNumero() == null || telefone.getTelNumero().equals("") || telefone.getTelNumero().length() < 9) {
                return "Numero telefone invalido! ";
            }
        }

        if (nomeClasse.equals("Cliente")) {

            Cliente cliente = (Cliente) entidade;

            if (cliente.getPesTelefone().getTelNumero() == null || cliente.getPesTelefone().getTelNumero().equals("") || cliente.getPesTelefone().getTelNumero().length() < 9) {
                return "Numero telefone invalido! ";
            }

        }
        return null;
    }

}
