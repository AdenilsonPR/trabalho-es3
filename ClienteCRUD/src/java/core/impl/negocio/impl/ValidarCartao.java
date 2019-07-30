package core.impl.negocio.impl;

import core.impl.negocio.IStrategy;
import dominio.Cartao;
import dominio.Cliente;
import dominio.EntidadeDominio;

public class ValidarCartao implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        String nomeClasse = entidade.getClass().getSimpleName();

        if (nomeClasse.equals("Cartao")) {

            Cartao cartao = (Cartao) entidade;

            if (cartao.getCarNumero() == null || cartao.getCarNumero().length() != 19) {
                return "O número do cartão está incorreto! ";
            }
            if (cartao.getCarCodSeguranca() == null || cartao.getCarCodSeguranca().length() != 3) {
                return "O código de segurança do cartão está incorreto! ";
            }
            if (cartao.getCarNomeImpresso() == null || cartao.getCarNomeImpresso().trim().equals("")) {
                return "O Nome impresso no catão é obrigatorio! ";
            }
            if (cartao.getCarValidade() == null || cartao.getCarValidade().length() < 5) {
                return "validade do cartão invalida! ";
            }
        }

        if (nomeClasse.equals("Cliente")) {

            Cliente cliente = (Cliente) entidade;

            for (int i = 0; i < cliente.getCliCartao().size(); i++) {
                if (cliente.getCliCartao().get(i).getCarNumero() == null || cliente.getCliCartao().get(i).getCarNumero().length() != 19) {
                    return "O número do cartão está incorreto! ";
                }
                if (cliente.getCliCartao().get(i).getCarCodSeguranca() == null || cliente.getCliCartao().get(i).getCarCodSeguranca().length() != 3) {
                    return "O código de segurança do cartão está incorreto! ";
                }
                if (cliente.getCliCartao().get(i).getCarNomeImpresso() == null || cliente.getCliCartao().get(i).getCarNomeImpresso().trim().equals("")) {
                    return "O Nome impresso no catão é obrigatorio! ";
                }
                if (cliente.getCliCartao().get(i).getCarValidade() == null || cliente.getCliCartao().get(i).getCarValidade().length() < 5) {
                    return "validade do cartão invalida! ";
                }
            }
        }
        return null;

    }
}
