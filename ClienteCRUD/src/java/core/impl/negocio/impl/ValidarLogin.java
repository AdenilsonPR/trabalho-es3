package core.impl.negocio.impl;

import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOLogin;
import core.impl.negocio.IStrategy;
import core.util.Criptografar;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Login;
import java.util.List;

public class ValidarLogin implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        String nomeClasse = entidade.getClass().getSimpleName();
        if (nomeClasse.equals("Login")) {

            Login login = (Login) entidade;
   

            if (login.getLgnSenhaAntida() == null || login.getLgnSenhaAntida().equals("")) {
                return "Senha invalido! ";
            }

            if (login.getLgnSenhaNova().length() < 8) {
                return "Senha minimo 8 caracteres! ";
            }

            if (login.getLgnConfirmarSenha() == null || login.getLgnConfirmarSenha().equals("") || login.getLgnSenhaNova().length() < 8) {
                return "Senha de confirmação invalido! ";
            }

            if (!login.getLgnSenhaNova().equals(login.getLgnConfirmarSenha())) {
                return "Senha e confirmação de senha não correspondem! ";
            }

            String senhaAntiga = login.getLgnSenhaAntida();
            Login l = new Login();
            IDAO dao = new DAOLogin();
            List<EntidadeDominio> entidadeDominios = dao.consultar(entidade);

            l = (Login) entidadeDominios.get(0);

            String senhaCriptografada = Criptografar.criaCriptografia(senhaAntiga);

            if (!l.getLgnSenhaAntida().equals(senhaCriptografada)) {
                return "Senha antiga incorreta! ";
            }

        }

        if (nomeClasse.equals("Cliente")) {

            Cliente cliente = (Cliente) entidade;

            if (cliente.getPesLogin().getLgnEmail() == null || cliente.getPesLogin().getLgnEmail().equals("")) {
                return "Email invalido! ";
            }

            if (cliente.getPesLogin().getLgnSenhaNova() == null || cliente.getPesLogin().getLgnSenhaNova().equals("")) {
                return "Senha invalido! ";
            }

            if (cliente.getPesLogin().getLgnSenhaNova().length() < 8) {
                return "Senha minimo 8 caracteres! ";
            }

            if (cliente.getPesLogin().getLgnConfirmarSenha() == null || cliente.getPesLogin().getLgnConfirmarSenha().equals("") || cliente.getPesLogin().getLgnSenhaNova().length() < 8) {
                return "Senha de confirmação invalido! ";
            }

            if (cliente.getPesLogin().getLgnSenhaNova() == null || !cliente.getPesLogin().getLgnSenhaNova().equals(cliente.getPesLogin().getLgnConfirmarSenha())) {
                return "Senha e confirmação de senha não correspondem! ";
            }
        }
        return null;
    }

}
