package web.viewHelper.impl;

import core.aplicacao.Resultado;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Login;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.viewHelper.IViewHelper;

public class ViewHelperLogin implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Login login = new Login();
        Cliente cliente = new Cliente();
        String operacao = request.getParameter("OPERACAO");

        if (operacao.equals("ALTERAR")) {
            String senhaAntiga = request.getParameter("txtAltLgnSenhaAntiga");
            String senhaNova = request.getParameter("txtAltLgnSenhaNova");
            String senhaNovaConfir = request.getParameter("txtAltLgnSenhaConfir");
            String senhaId = request.getParameter("txtAlterarSenhaId");

            login.setLgnSenhaAntida(senhaAntiga);
            login.setLgnSenhaNova(senhaNova);
            login.setLgnConfirmarSenha(senhaNovaConfir);
            login.setEtdId(Integer.valueOf(senhaId));

        } else {
            HttpSession session = request.getSession();

            Resultado resultado = (Resultado) session.getAttribute("clienteSessao");
            String loginId = request.getParameter("txtLoginId");

            if (loginId != null && !loginId.trim().equals("")) {

                int id = Integer.parseInt(loginId);

                for (EntidadeDominio e : resultado.getEntidades()) {
                    cliente = (Cliente) e;
                    if (cliente.getPesLogin().getEtdId() == id) {
                        login = cliente.getPesLogin();
                    }
                }
            }
        }

        return login;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String operacao = request.getParameter("OPERACAO");
        RequestDispatcher dispatcher = null;

        if (operacao.equals("VISUALIZAR")) {
            request.setAttribute("resultado", resultado);
            request.getSession().setAttribute("VisualizarLogin", resultado.getEntidades().get(0));
            request.getSession().setAttribute("loginSessao", resultado);
            dispatcher = request.getRequestDispatcher("login/FormVisualizarLogin.jsp");

        } else if (operacao.equals("ALTERAR")) {
            if (resultado.getMsg().equals("Alterado com sucesso.")) {

                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("loginSessao", resultado);
                dispatcher = request.getRequestDispatcher("/ConsultarCliente?OPERACAO=CONSULTAR");
            } else {
                request.setAttribute("resultado", resultado);
                request.getSession().setAttribute("loginSessao", resultado);
                dispatcher = request.getRequestDispatcher("login/FormVisualizarLogin.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

}
