package core.impl.controle.impl;

import core.aplicacao.Resultado;
import core.impl.controle.IFachada;
import core.impl.dao.IDAO;
import core.impl.dao.impl.DAOCartao;
import core.impl.dao.impl.DAOCliente;
import core.impl.dao.impl.DAOEndereco;
import core.impl.dao.impl.DAOLogin;
import core.impl.negocio.IStrategy;
import core.impl.negocio.impl.ComplementarDtCad;
import core.impl.negocio.impl.ValidarCartao;
import core.impl.negocio.impl.ValidarCpf;
import core.impl.negocio.impl.ValidarCliente;
import core.impl.negocio.impl.ValidarEndereco;
import core.impl.negocio.impl.ValidarExistencia;
import core.impl.negocio.impl.ValidarLogin;
import core.impl.negocio.impl.ValidarTelefone;
import core.impl.negocio.impl.VerificaCartaoPreferencial;
import core.impl.negocio.impl.VerificaCartaoPreferencialExcluir;
import core.impl.negocio.impl.VerificaEnderecoCobranca;
import core.impl.negocio.impl.VerificaEnderecoCobrancaExcluir;
import core.impl.negocio.impl.VerificaEnderecoEntrega;
import core.impl.negocio.impl.VerificaEnderecoEntregaExcluir;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Login;
import dominio.Telefone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fachada implements IFachada {

    private Map<String, IDAO> daos;
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado;

    public Fachada() {

        daos = new HashMap<String, IDAO>();
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        daos.put(Cliente.class.getName(), new DAOCliente());
        daos.put(Endereco.class.getName(), new DAOEndereco());
        daos.put(Cartao.class.getName(), new DAOCartao());
        daos.put(Login.class.getName(), new DAOLogin());

        List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
        rnsSalvarCliente.add(new ComplementarDtCad());
        rnsSalvarCliente.add(new ValidarExistencia());
        rnsSalvarCliente.add(new ValidarCpf());
        rnsSalvarCliente.add(new ValidarCartao());
        rnsSalvarCliente.add(new ValidarEndereco());
        rnsSalvarCliente.add(new ValidarCliente());
        rnsSalvarCliente.add(new ValidarLogin());
        rnsSalvarCliente.add(new ValidarTelefone());

        List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
        rnsAlterarCliente.add(new ValidarCliente());
        rnsAlterarCliente.add(new ValidarTelefone());

        List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
        rnsSalvarEndereco.add(new ValidarEndereco());
        rnsSalvarEndereco.add(new VerificaEnderecoCobranca());
        rnsSalvarEndereco.add(new VerificaEnderecoEntrega());

        List<IStrategy> rnsAlterarEndereco = new ArrayList<IStrategy>();
        rnsAlterarEndereco.add(new ValidarEndereco());
        rnsAlterarEndereco.add(new VerificaEnderecoCobranca());
        rnsAlterarEndereco.add(new VerificaEnderecoEntrega());

        List<IStrategy> rnsExcluirEndereco = new ArrayList<IStrategy>();
        rnsExcluirEndereco.add(new VerificaEnderecoCobrancaExcluir());
        rnsExcluirEndereco.add(new VerificaEnderecoEntregaExcluir());

        List<IStrategy> rnsAlterarLogin = new ArrayList<IStrategy>();
        rnsAlterarLogin.add(new ValidarLogin());

        List<IStrategy> rnsSalvarTelefone = new ArrayList<IStrategy>();
        rnsSalvarTelefone.add(new ValidarTelefone());

        List<IStrategy> rnsSalvarCartao = new ArrayList<IStrategy>();
        rnsSalvarCartao.add(new ValidarCartao());
        rnsSalvarCartao.add(new VerificaCartaoPreferencial());

        List<IStrategy> rnsAlterarCartao = new ArrayList<IStrategy>();
        rnsAlterarCartao.add(new ValidarCartao());
        rnsAlterarCartao.add(new VerificaCartaoPreferencial());

        List<IStrategy> rnsExcluirCartao = new ArrayList<IStrategy>();
        rnsExcluirCartao.add(new VerificaCartaoPreferencialExcluir());

        Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
        rnsCliente.put("SALVAR", rnsSalvarCliente);
        rnsCliente.put("ALTERAR", rnsAlterarCliente);

        Map<String, List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
        rnsEndereco.put("SALVAR", rnsSalvarEndereco);
        rnsEndereco.put("ALTERAR", rnsAlterarEndereco);
        rnsEndereco.put("EXCLUIR", rnsExcluirEndereco);

        Map<String, List<IStrategy>> rnsTelefone = new HashMap<String, List<IStrategy>>();
        rnsTelefone.put("SALVAR", rnsSalvarTelefone);

        Map<String, List<IStrategy>> rnsCartao = new HashMap<String, List<IStrategy>>();
        rnsCartao.put("SALVAR", rnsSalvarCartao);
        rnsCartao.put("ALTERAR", rnsAlterarCartao);
        rnsCartao.put("EXCLUIR", rnsExcluirCartao);

        Map<String, List<IStrategy>> rnsLogin = new HashMap<String, List<IStrategy>>();
        rnsLogin.put("ALTERAR", rnsAlterarLogin);

        rns.put(Cliente.class.getName(), rnsCliente);
        rns.put(Endereco.class.getName(), rnsEndereco);
        rns.put(Telefone.class.getName(), rnsTelefone);
        rns.put(Cartao.class.getName(), rnsCartao);
        rns.put(Login.class.getName(), rnsLogin);
    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        String msg = executarRegras(entidade, "SALVAR");

        if (msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.salvar(entidade);
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                resultado.setMsg("Salvo com sucesso.");

            } catch (Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possivel registrar!");
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            }
        } else {
            resultado.setMsg(msg);
            entidades.add(entidade);
            resultado.setEntidades(entidades);

        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        String msg = executarRegras(entidade, "ALTERAR");

        if (msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.alterar(entidade);

                entidades.add(entidade);
                resultado.setEntidades(entidades);
                resultado.setMsg("Alterado com sucesso.");

            } catch (Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possivel alterar!");

            }
        } else {
            resultado.setMsg(msg);
            entidades.add(entidade);
            resultado.setEntidades(entidades);

        }

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "EXCLUIR");

        if (msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.excluir(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                resultado.setMsg("Excluido com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possivel excluir!");

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {

        resultado = new Resultado();

        String msg = executarRegras(entidade, "CONSULTAR");
        String nomeClasse = entidade.getClass().getName();

        if (msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                if (entidade.getEtdId() == 0) {
                    resultado.setEntidades(dao.consultar(entidade));
                } else {
                    resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
                    resultado.getEntidades().add(entidade);
                }
            } catch (Exception e) {
                resultado.setMsg("Não foi possível consultar!");
            }
        } else {
            List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
            entidades.add(entidade);
            resultado.setEntidades(entidades);
            resultado.setMsg(msg);
        }
        return resultado;
    }

    private String executarRegras(EntidadeDominio entidade, String operacao) {
        String nomeClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();
        Map<String, List<IStrategy>> regrasOperacao = rns.get(nomeClasse);
        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operacao);
            if (regras != null) {
                for (IStrategy s : regras) {
                    String m = s.processar(entidade);
                    if (m != null) {
                        msg.append(m);
                    }
                }
            }
        }
        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }

}
