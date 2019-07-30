package web.viewHelper.impl;

import core.aplicacao.Resultado;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.viewHelper.IViewHelper;

public class ViewHelperEndereco implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        String operacao = request.getParameter("OPERACAO");

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
            String endCliId = request.getParameter("txtEndCliId");
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
            if (endCliId != null && !endCliId.trim().equals("")) {
                endereco.setEndCliId(Integer.valueOf(endCliId));
            }
            if (endCidade != null && !endBairro.trim().equals("")) {
                endereco.setEndBairro(endBairro);
                endereco.setEtdStatus("ativo");
            }

            if (request.getParameter("OPERACAO").equals("ALTERAR")) {

                int endId = Integer.valueOf(request.getParameter("txtAlterarEnderecoId"));
                endereco.setEtdId(endId);
            }
        } else {
            HttpSession session = request.getSession();

            Resultado resultado = (Resultado) session.getAttribute("clienteSessao");
            String listaId = request.getParameter("txtListaId");

            if (listaId != null && !listaId.trim().equals("")) {

                int id = Integer.parseInt(listaId);

                for (EntidadeDominio e : resultado.getEntidades()) {
                    cliente = (Cliente) e;
                    for (int i = 0; i < cliente.getPesEndereco().size(); i++) {
                        if (cliente.getPesEndereco().get(i).getEtdId() == id) {
                            endereco = cliente.getPesEndereco().get(i);
                        }
                    }
                }
            }
        }
        return endereco;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String operacao = request.getParameter("OPERACAO");
        RequestDispatcher dispatcher = null;

        if (operacao.equals("SALVAR")) {
            if (resultado.getMsg().equals("Salvo com sucesso.")) {

                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("enderecoSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");

            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("enderecoSessao", resultado);
                dispatcher = request.getRequestDispatcher("endereco/FormCadastrarEndereco.jsp");
            }
        } else if (operacao.equals("VISUALIZAR")) {

            request.setAttribute("resultado", resultado);
            request.getSession().setAttribute("VisualizarEndereco", resultado.getEntidades().get(0));
            request.getSession().setAttribute("enderecoSessao", resultado);
            dispatcher = request.getRequestDispatcher("endereco/FormVisualizarEndereco.jsp");

        } else if (operacao.equals("ALTERAR")) {

            if (resultado.getMsg().equals("Alterado com sucesso.")) {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("enderecoSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("enderecoSessao", resultado);
                dispatcher = request.getRequestDispatcher("endereco/FormVisualizarEndereco.jsp");
            }

        } else if (operacao.equals("EXCLUIR")) {

            request.setAttribute("resultado", resultado);
            dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
        }

        dispatcher.forward(request, response);

    }

}
