<%@page import="dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="core.aplicacao.Resultado"%>
<%@page import="dominio.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>Biblioteca</title>
    </head>

    <body>
        <%
            Login login = (Login) session.getAttribute("VisualizarLogin");
            Resultado resultado = (Resultado) request.getAttribute("resultado");

        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ConsultarCliente?OPERACAO=CONSULTAR" class="btn btn-default">Lista</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/cliente/FormCadastrarCliente.jsp">Cadastrar</a>
                    </li>

                </ul>
            </div>
        </nav>
        </br>
        <div class="row"> 
            <div class="form-group col-4"></div>

            <div class="form-group col-4">    
                <div class="container card-header">
                    <h3>Login</h3>
                </div>
            </div>
        </div>
        <div class="row"> 
            <div class="form-group col-4"></div>
            <div >
                <h5>
                    <%                        if (resultado != null && resultado.getMsg() != null) {
                            out.print(resultado.getMsg());
                        }
                    %>
                </h5>
            </div>
        </div>

        <div class="container card-body">
            <form action="${pageContext.request.contextPath}/AlterarLogin">
                <div class="row">
                    <input type="hidden" class="form-control" value="<%= login.getEtdId()%>" id="txtAlterarSenhaId" name="txtAlterarSenhaId">

                    <div class="form-group col-4"></div>

                    <div class="form-group col-4">
                        <label for="txtAltLgnSenhaAntiga">Senha antiga: </label>
                        <input type="password" class="form-control" id="txtAltLgnSenhaAntiga" name="txtAltLgnSenhaAntiga" maxlength="8">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-4"></div>

                    <div class="form-group col-4">
                        <label for="txtAltLgnSenhaNova">Nova senha: </label>
                        <input type="password" class="form-control" id="txtAltLgnSenhaNova" name="txtAltLgnSenhaNova" maxlength="8">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-4"></div>

                    <div class="form-group col-4">
                        <label for="txtAltLgnSenhaConfir">Confirmar senha: </label>
                        <input type="password" class="form-control" id="txtAltLgnSenhaConfir" name="txtAltLgnSenhaConfir" maxlength="8">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-4"></div>

                    <div class="form-group col-5">
                        <button type="submit" class="btn btn-success" name="OPERACAO" value="ALTERAR">Alterar</button>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/ConsultarCliente?txtPesquisa=ativo&OPERACAO=VISUALIZAR">Cancelar</a>
                    </div>


                </div>
            </form>
        </div>
    </body>
</html>
