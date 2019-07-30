<%@page import="core.util.ConverterData"%>
<%@page import="dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="dominio.EntidadeDominio"%>
<%@page import="dominio.EntidadeDominio"%>
<%@page import="core.aplicacao.Resultado"%>
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
            Resultado resultado = (Resultado) session.getAttribute("clienteSessao");
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/ConsultarCliente?OPERACAO=CONSULTAR" class="btn btn-default">Lista</a>
                    </li>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cliente/FormCadastrarCliente.jsp">Cadastrar</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="col-12">
            <div class="card-header ">
                <div class="row">
                    <div class="col-3">
                        <h3 >Lista de Clientes</h3>
                    </div>
                    <div class="col-3">
                        <h5>
                            <%
                                if (resultado != null && resultado.getMsg() != null) {
                                    out.print(resultado.getMsg());
                                }
                            %>
                        </h5>
                    </div>
                    <div class="col-2"></div>
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/ConsultarCliente">
                        <input type="text" class="form-control" id="txtPesquisa"name="txtPesquisa" placeholder="Pesquisa">
                        <input class="btn btn-info" type="submit" id="OPERACAO" name="OPERACAO" value="CONSULTAR" />
                    </form>
                </div>
            </div>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID:</th>
                        <th>Nome: </th>
                        <th>Genero:</th>
                        <th>CPF:</th>
                        <th>Telefone:</th>
                        <th>Email:</th>
                    </tr>
                </thead>

                <%
                    if (resultado != null) {
                        List<EntidadeDominio> clientes = resultado.getEntidades();

                        if (clientes != null) {
                            for (int i = 0; i < clientes.size(); i++) {
                                Cliente c = (Cliente) clientes.get(i);

                                if (c.getEtdStatus().equals("ativo")) {
                %>
                <tr>
                    <td><%= c.getEtdId()%></td>
                    <td><%= c.getPesNome()%></td>
                    <td><%= c.getPesGenero()%></td>
                    <td><%= c.getCliCPF()%></td>
                    <td><%= c.getPesTelefone().getTelNumero()%></td>
                    <td><%= c.getPesLogin().getLgnEmail()%></td>
                    <td><a href="${pageContext.request.contextPath}/ConsultarCliente?txtListaId=<%=c.getEtdId()%>&OPERACAO=VISUALIZAR" class="btn btn-default" >Visualizar</a></td>
                    <td><a href="${pageContext.request.contextPath}/ExcluirCliente?OPERACAO=EXCLUIR&txtListaId=<%=c.getEtdId()%>" class="btn btn-default" >Excluir</a></td>
                </tr>

                <%
                                }
                            }
                        }
                    }
                %>

            </table>
        </div>
    </body>
</html>
