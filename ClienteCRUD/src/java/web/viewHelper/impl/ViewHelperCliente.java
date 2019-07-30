package web.viewHelper.impl;

import core.aplicacao.Resultado;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Login;
import dominio.Telefone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.viewHelper.IViewHelper;

public class ViewHelperCliente implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("OPERACAO");

        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        List<Endereco> enderecos = new ArrayList<>();
        Login login = new Login();
        Telefone telefone = new Telefone();
        List<Cartao> cartoes = new ArrayList<>();
        Cartao cartao = new Cartao();
        Random r = new Random();

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("EXCLUIR") && !operacao.equals("CONSULTAR")) {

            // Dados de Endereco.
            String endTipo = request.getParameter("txtEndTipo");
            String endTipoLogradouro = request.getParameter("txtEndTipoLogradouro");
            String endLogradouro = request.getParameter("txtEndLogradouro");
            String endNumero = request.getParameter("txtEndNumero");
            String endPais = request.getParameter("txtEndPais");
            String endCEP = request.getParameter("txtEndCEP");
            String endEstado = request.getParameter("txtEndEstado");
            String endCidade = request.getParameter("txtEndCidade");
            String endBairro = request.getParameter("txtEndBairro");
            String endCobranca = request.getParameter("txtEndCobranca");
            String endEntrega = request.getParameter("txtEndEntrega");

            endereco.setEndIsCobranca(endCobranca);
            endereco.setEndIsEntrega(endEntrega);

            if (endTipo != null && !endTipo.trim().equals("")) {
                endereco.setEndTipo(endTipo);
            }
            if (endTipoLogradouro != null && !endTipoLogradouro.trim().equals("")) {
                endereco.setEndTipoLogradouro(endTipoLogradouro);
            }
            if (endLogradouro != null && !endLogradouro.trim().equals("")) {
                endereco.setEndLogradouro(endLogradouro);
            }
            if (endNumero != null && !endNumero.trim().equals("")) {
                endereco.setEndNumero(endNumero);
            }
            if (endPais != null && !endPais.trim().equals("")) {
                endereco.setEndPais(endPais);
            }
            if (endCEP != null && !endCEP.trim().equals("")) {
                endereco.setEndCEP(endCEP);
            }
            if (endEstado != null && !endEstado.trim().equals("")) {
                endereco.setEndEstado(endEstado);
            }
            if (endCidade != null && !endCidade.trim().equals("")) {
                endereco.setEndCidade(endCidade);
            }
            if (endCidade != null && !endBairro.trim().equals("")) {
                endereco.setEndBairro(endBairro);
                endereco.setEtdStatus("ativo");
            }

            // Dados de Login.
            String lgnEmail = request.getParameter("txtCadLgnEmail");
            String lgnSenha = request.getParameter("txtCadLgnSenha");
            String lgnConfirmarSenha = request.getParameter("txtCadLgnConSenha");

            if (lgnEmail != null && !lgnEmail.trim().equals("")) {
                login.setLgnEmail(lgnEmail);
            }
            if (lgnSenha != null && !lgnSenha.trim().equals("")) {
                login.setLgnSenhaNova(lgnSenha);
            }
            if (lgnConfirmarSenha != null && !lgnConfirmarSenha.trim().equals("")) {
                login.setLgnConfirmarSenha(lgnConfirmarSenha);
                login.setEtdStatus("ativo");
            }

            // Dados de Telefone.
            String telTipo = request.getParameter("txtTelTipo");
            String telDDD = request.getParameter("txtTelDDD");
            String telNumero = request.getParameter("txtTelNumero");

            if (telTipo != null && !telTipo.trim().equals("")) {
                telefone.setTelTipo(telTipo);
            }
            if (telDDD != null && !telDDD.trim().equals("")) {
                telefone.setTelDDD(telDDD);
            }
            if (telNumero != null && !telNumero.trim().equals("")) {
                telefone.setTelNumero(telNumero);
                telefone.setEtdStatus("ativo");
            }

            // Dados de Cartao.
            String carBandeira = request.getParameter("txtCarBandeira");
            String carNumero = request.getParameter("txtCarNumero");
            String carNomeImpresso = request.getParameter("txtCarNomeImpresso");
            String carCodSeguranca = request.getParameter("txtCarCodSeguranca");
            String carValidade = request.getParameter("txtCarValidade");
            String carPreferencial = request.getParameter("txtCarPreferencial");
            cartao.setCarIsPreferencial(carPreferencial);

            if (carBandeira != null && !carBandeira.trim().equals("")) {
                cartao.setCarBandeira(carBandeira);
            }
            if (carNumero != null && !carNumero.trim().equals("")) {
                cartao.setCarNumero(carNumero);
            }
            if (carNomeImpresso != null && !carNomeImpresso.trim().equals("")) {
                cartao.setCarNomeImpresso(carNomeImpresso);
            }
            if (carCodSeguranca != null && !carCodSeguranca.trim().equals("")) {
                cartao.setCarCodSeguranca(carCodSeguranca);
            }
            if (carValidade != null && !carValidade.trim().equals("")) {
                cartao.setCarValidade(carValidade);
                cartao.setEtdStatus("ativo");
            }

            // Dados do Cliente.
            String cliNome = request.getParameter("txtNome");
            String cliCPF = request.getParameter("txtCPF");
            String cliDataNascimento = request.getParameter("txtDataNascimento");
            String cliGenero = request.getParameter("txtGenero");

            if (cliNome != null && !cliNome.trim().equals("")) {
                cliente.setPesNome(cliNome);
            }
            if (cliCPF != null && !cliCPF.trim().equals("")) {
                cliente.setCliCPF(cliCPF);
            }
            if (cliDataNascimento != null && !cliDataNascimento.trim().equals("")) {
                cliente.setPesDataNascimento(cliDataNascimento);
            }
            if (cliGenero != null && !cliGenero.trim().equals("")) {
                cliente.setPesGenero(cliGenero);
            }

            if (endereco != null) {
                enderecos.add(endereco);
                cliente.setPesEndereco(enderecos);
            }
            if (login != null) {
                cliente.setPesLogin(login);
            }
            if (telefone != null) {
                cliente.setPesTelefone(telefone);
            }

            if (cartao != null) {
                cartoes.add(cartao);
                cliente.setCliCartao(cartoes);
                cliente.setCliRank(r.nextInt(11));
                cliente.setEtdStatus("ativo");
            }

            if (operacao.equals("ALTERAR")) {

                int cliId = Integer.valueOf(request.getParameter("txtAlterarClienteId"));
                cliente.setEtdId(cliId);
            }

        } else {

            HttpSession session = request.getSession();
            Resultado resultado = (Resultado) session.getAttribute("clienteSessao");
            String listaId = request.getParameter("txtListaId");
            String pesquisa = request.getParameter("txtPesquisa");

            if (pesquisa != null && !pesquisa.trim().equals("")) {
                cliente.setCliPesquisa(pesquisa);
            }

            if (!operacao.equals("CONSULTAR")) {
                if (listaId != null && !listaId.trim().equals("")) {

                    int id = Integer.parseInt(listaId);

                    for (EntidadeDominio e : resultado.getEntidades()) {
                        if (e.getEtdId() == id) {
                            cliente = (Cliente) e;
                        }
                    }
                }
            } else {
                cliente.setEtdId(0);
            }

        }
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String operacao = request.getParameter("OPERACAO");
        RequestDispatcher dispatcher = null;

        if (operacao.equals("SALVAR")) {
            if (resultado.getMsg().equals("Salvo com sucesso.")) {

                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("clienteSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR");

            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("clienteSessao", resultado);
                dispatcher = request.getRequestDispatcher("cliente/FormCadastrarCliente.jsp");
            }
        } else if (operacao.equals("CONSULTAR")) {

            request.getSession().setAttribute("clienteSessao", resultado);
            dispatcher = request.getRequestDispatcher("cliente/FormListarCliente.jsp");

        } else if (operacao.equals("VISUALIZAR")) {

            request.getSession().setAttribute("VisualizarCliente", resultado.getEntidades().get(0));
            dispatcher = request.getRequestDispatcher("cliente/FormVisualizarCliente.jsp");

        } else if (operacao.equals("ALTERAR")) {

            if (resultado.getMsg().equals("Alterado com sucesso.")) {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("clienteSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR");
            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("enderecoSessao", resultado);
                dispatcher = request.getRequestDispatcher("cliente/FormVisualizarCliente.jsp");
            }

        } else if (operacao.equals("EXCLUIR")) {

            request.setAttribute("resultado", resultado);
            dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
        }

        dispatcher.forward(request, response);
    }

}
