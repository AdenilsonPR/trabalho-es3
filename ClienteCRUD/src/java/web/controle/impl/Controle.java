package web.controle.impl;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.command.ICommand;
import web.command.impl.CommandAlterar;
import web.command.impl.CommandConsultar;
import web.command.impl.CommandExcluir;
import web.command.impl.CommandSalvar;
import web.command.impl.CommandVisualizar;
import web.viewHelper.IViewHelper;
import web.viewHelper.impl.ViewHelperCartao;
import web.viewHelper.impl.ViewHelperCliente;
import web.viewHelper.impl.ViewHelperEndereco;
import web.viewHelper.impl.ViewHelperLogin;

public class Controle extends HttpServlet {

    private static Map<String, ICommand> commands;
    private static Map<String, IViewHelper> vhs;

    public Controle() {
        commands = new HashMap<>();

        commands.put("ALTERAR", new CommandAlterar());
        commands.put("CONSULTAR", new CommandConsultar());
        commands.put("VISUALIZAR", new CommandVisualizar());
        commands.put("SALVAR", new CommandSalvar());
        commands.put("EXCLUIR", new CommandExcluir());

        vhs = new HashMap<>();

        vhs.put("/ClienteCRUD/AlterarCliente", new ViewHelperCliente());
        vhs.put("/ClienteCRUD/AlterarEndereco", new ViewHelperEndereco());
        vhs.put("/ClienteCRUD/AlterarCartao", new ViewHelperCartao());
        vhs.put("/ClienteCRUD/AlterarLogin", new ViewHelperLogin());

        vhs.put("/ClienteCRUD/ConsultarCliente", new ViewHelperCliente());
        vhs.put("/ClienteCRUD/ConsultarEndereco", new ViewHelperEndereco());
        vhs.put("/ClienteCRUD/ConsultarCartao", new ViewHelperCartao());
        vhs.put("/ClienteCRUD/ConsultarLogin", new ViewHelperLogin());

        vhs.put("/ClienteCRUD/SalvarCliente", new ViewHelperCliente());
        vhs.put("/ClienteCRUD/SalvarEndereco", new ViewHelperEndereco());
        vhs.put("/ClienteCRUD/SalvarCartao", new ViewHelperCartao());
        vhs.put("/ClienteCRUD/SalvarLogin", new ViewHelperLogin());

        vhs.put("/ClienteCRUD/ExcluirCliente", new ViewHelperCliente());
        vhs.put("/ClienteCRUD/ExcluirEndereco", new ViewHelperEndereco());
        vhs.put("/ClienteCRUD/ExcluirCartao", new ViewHelperCartao());
        vhs.put("/ClienteCRUD/ExcluirLogin", new ViewHelperLogin());

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        String operacao = request.getParameter("OPERACAO");

        IViewHelper vh = vhs.get(uri);

        EntidadeDominio entidade = vh.getEntidade(request);

        ICommand command = commands.get(operacao);

        Resultado resultado = command.execute(entidade);

        vh.setView(resultado, request, response);

    }

}
