package web.viewHelper.impl;

import core.aplicacao.Resultado;
import dominio.Cartao;
import dominio.Cliente;
import dominio.EntidadeDominio;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.viewHelper.IViewHelper;

public class ViewHelperCartao implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Cartao cartao = new Cartao();
        Cliente cliente = new Cliente();
        String operacao = request.getParameter("OPERACAO");

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("EXCLUIR") && !operacao.equals("CONSULTAR")) {

            String carBandeira = request.getParameter("txtCarBandeira");
            String carNumero = request.getParameter("txtCarNumero");
            String carNomeImpresso = request.getParameter("txtCarNomeImpresso");
            String carCodSeguranca = request.getParameter("txtCarCodSeguranca");
            String carCliId = request.getParameter("txtCarCliId");
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
            if (carCliId != null && !carCliId.trim().equals("")) {
                cartao.setCarCliId(Integer.valueOf(carCliId));
            }
            if (carCodSeguranca != null && !carCodSeguranca.trim().equals("")) {
                cartao.setCarCodSeguranca(carCodSeguranca);
            }
            if (carValidade != null && !carValidade.trim().equals("")) {
                cartao.setCarValidade(carValidade);
                cartao.setEtdStatus("ativo");
            }

            if (request.getParameter("OPERACAO").equals("ALTERAR")) {

                int carId = Integer.valueOf(request.getParameter("txtAlterarCartaoId"));
                cartao.setEtdId(carId);
            }

        } else {
            HttpSession session = request.getSession();

            Resultado resultado = (Resultado) session.getAttribute("clienteSessao");
            String listaId = request.getParameter("txtListaId");

            if (listaId != null && !listaId.trim().equals("")) {

                int id = Integer.parseInt(listaId);

                for (EntidadeDominio e : resultado.getEntidades()) {
                    cliente = (Cliente) e;
                    for (int i = 0; i < cliente.getCliCartao().size(); i++) {
                        if (cliente.getCliCartao().get(i).getEtdId() == id) {
                            cartao = cliente.getCliCartao().get(i);
                        }
                    }
                }
            }
        }
        return cartao;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("OPERACAO");
        RequestDispatcher dispatcher = null;

        if (operacao.equals("SALVAR")) {
            if (resultado.getMsg().equals("Salvo com sucesso.")) {

                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("cartaoSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");

            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("cartaoSessao", resultado);
                dispatcher = request.getRequestDispatcher("cartao/FormCadastrarCartao.jsp");
            }
        } else if (operacao.equals("VISUALIZAR")) {

            request.setAttribute("resultado", resultado);
            request.getSession().setAttribute("VisualizarCartao", resultado.getEntidades().get(0));
            request.getSession().setAttribute("cartaoSessao", resultado);
            dispatcher = request.getRequestDispatcher("cartao/FormVisualizarCartao.jsp");

        } else if (operacao.equals("ALTERAR")) {
            if (resultado.getMsg().equals("Alterado com sucesso.")) {

                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("cartaoSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("cartaoSessao", resultado);
                dispatcher = request.getRequestDispatcher("cartao/FormVisualizarCartao.jsp");
            }

        } else if (operacao.equals("EXCLUIR")) {

            request.setAttribute("resultado", resultado);
            dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
        }
        dispatcher.forward(request, response);
    }

}
