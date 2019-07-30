<%@page import="core.util.ConverterData"%>
<%@page import="dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="dominio.Telefone"%>
<%@page import="dominio.Endereco"%>
<%@page import="dominio.Login"%>
<%@page import="dominio.Cartao"%>
<%@page import="core.aplicacao.Resultado"%>
<%@page import="dominio.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script language="JavaScript" src="${pageContext.request.contextPath}/bootstrap/js/filtros.js"></script>
        <title>Biblioteca</title>
    </head>

    <body>
        <%
            Resultado resultado = (Resultado) request.getAttribute("resultado");
            Cliente cliente = (Cliente) session.getAttribute("VisualizarCliente");
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
        <div class="container ">
            <form action="${pageContext.request.contextPath}/AlterarCliente">

                <div class="card-header">
                    <h3>Dados Pessoais</h3>
                </div>
                <div>
                    <h5>
                        <%
                            if (resultado != null && resultado.getMsg() != null) {
                                out.print(resultado.getMsg());

                            }
                        %>
                    </h5>
                </div>
                </br>       
                <div class="row">
                    <div class="form-group col-1">
                        <label for="txtAlterarClienteId">Id: </label>
                        <input type="text" class="form-control" value="<%=cliente.getEtdId()%>" id="txtAlterarClienteId" name="txtAlterarClienteId" readonly>
                    </div>

                    <div class="form-group col-5">
                        <label for="txtNome">Nome: </label>
                        <input type="text" class="form-control" value="<%= cliente.getPesNome()%>" id="txtNome" name="txtNome" placeholder="Nome completo" maxlength="30">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtGenero">Genero: </label>
                        <select class="form-control" id="txtGenero" name="txtGenero">
                            <option value="Feminino" <%= cliente.getPesGenero().equals("Feminino") ? "selected" : " "%>>Feminino</option>
                            <option value="Masculino" <%= cliente.getPesGenero().equals("Masculino") ? "selected" : " "%>>Masculino</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCPF">CPF: </label>
                        <input type="text" class="form-control" value="<%= cliente.getCliCPF()%>"id="txtCPF" name="txtCPF" onkeypress=" mascara(this, '###.###.###-##'); return SomenteNumero(event)" placeholder="000.000.000-00" maxlength="14" readonly>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtDataNascimento">Data de nascimento: </label>
                        <input type="text" class="form-control" value="<%= cliente.getPesDataNascimento()%>"id="txtDataNascimento" name="txtDataNascimento" onkeypress="mascara(this, '##/##/####'); return SomenteNumero(event)" placeholder="dd/mm/aaaa" maxlength="10">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtTelTipo">Tipo de Telefone: </label>
                        <select class="form-control" id="txtTelTipo" name="txtTelTipo">
                            <option value="Fixo" <%= cliente.getPesTelefone().getTelTipo().equals("Fixo") ? "selected" : " "%>>Fixo</option>
                            <option value="Celular" <%= cliente.getPesTelefone().getTelTipo().equals("Celular") ? "selected" : " "%>>Celular</option>
                        </select>
                    </div>

                    <div class="form-group col-1">
                        <label for="txtTelDDD">DDD: </label>
                        <select class="form-control" id="txtTelDDD" name="txtTelDDD">
                            <option value="11" <%= cliente.getPesTelefone().getTelDDD().equals("11") ? "selected" : " "%>>11</option>
                            <option value="12" <%= cliente.getPesTelefone().getTelDDD().equals("12") ? "selected" : " "%>>12</option>
                            <option value="13" <%= cliente.getPesTelefone().getTelDDD().equals("13") ? "selected" : " "%>>13</option>
                            <option value="14" <%= cliente.getPesTelefone().getTelDDD().equals("14") ? "selected" : " "%>>14</option>
                            <option value="15" <%= cliente.getPesTelefone().getTelDDD().equals("15") ? "selected" : " "%>>15</option>
                            <option value="16" <%= cliente.getPesTelefone().getTelDDD().equals("16") ? "selected" : " "%>>16</option>
                            <option value="17" <%= cliente.getPesTelefone().getTelDDD().equals("17") ? "selected" : " "%>>17</option>
                            <option value="18" <%= cliente.getPesTelefone().getTelDDD().equals("18") ? "selected" : " "%>>18</option>
                            <option value="19" <%= cliente.getPesTelefone().getTelDDD().equals("19") ? "selected" : " "%>>19</option>
                            <option value="20" <%= cliente.getPesTelefone().getTelDDD().equals("20") ? "selected" : " "%>>20</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtTelNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= cliente.getPesTelefone().getTelNumero()%>" id="txtTelNumero" name="txtTelNumero" onkeypress="mascara(this, '#####-####'); return SomenteNumero(event)" placeholder="00000-0000" maxlength="10">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtRank">Rank: </label>
                        <input type="text" class="form-control" value="<%= cliente.getCliRank()%>" id="txtRank" name="txtRank" readonly>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtDataCadastro">Data de cadastro: </label>
                        <input type="text" class="form-control" value="<%= ConverterData.converteDateString(cliente.getEtdDataCadastro())%>" id="txtDataCadastro" name="txtDataCadastro" readonly>
                    </div>
                </div>

                </br>                
                <div class="container card-header">
                    <h3>Dados do Endereço</h3>
                </div>

                <div class="row">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID:</th>
                                <th>Cobrança:</th>
                                <th>Entrega:</th>
                                <th>Tipo: </th>
                                <th>Logradouro:</th>
                                <th>Cidade:</th>
                                <th>Estado:</th>
                                <th>CEP:</th>
                                <th><a href="${pageContext.request.contextPath}/endereco/FormCadastrarEndereco.jsp">Novo</a></th>
                            </tr>
                        </thead>

                        <%
                            int contEnd = 0;

                            for (int i = 0; i < cliente.getPesEndereco().size(); i++) {
                                if (cliente.getPesEndereco().get(i).getEtdStatus().equals("ativo") && cliente.getPesEndereco().get(i).getEndCliId() == cliente.getEtdId()) {
                                    contEnd++;
                                }
                            }
                            for (int i = 0; i < cliente.getPesEndereco().size(); i++) {

                                if (cliente.getPesEndereco().get(i).getEtdStatus().equals("ativo") && cliente.getPesEndereco().get(i).getEndCliId() == cliente.getEtdId()) {
                        %>
                        <tr>
                            <td><%= cliente.getPesEndereco().get(i).getEtdId()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndIsCobranca()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndIsEntrega()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndTipo()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndLogradouro()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndCidade()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndEstado()%></td>
                            <td><%= cliente.getPesEndereco().get(i).getEndCEP()%></td>
                            <td><a href="${pageContext.request.contextPath}/ConsultarEndereco?txtListaId=<%=cliente.getPesEndereco().get(i).getEtdId()%>&OPERACAO=VISUALIZAR" class="btn btn-default" >Visualizar</a></td>
                            <%if (contEnd > 1) {%>
                            <td><a href="${pageContext.request.contextPath}/ExcluirEndereco?OPERACAO=EXCLUIR&txtListaId=<%=cliente.getPesEndereco().get(i).getEtdId()%>" class="btn btn-default" >Excluir</a></td>
                            <%}%>
                        </tr>

                        <%
                                }
                            }
                        %>

                    </table>

                </div>

                </br>                
                <div class="container card-header">
                    <h3>Dados dos Cartões</h3>
                </div>
                </br>                

                <div class="row">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID:</th>
                                <th>Preferencial:</th>
                                <th>Bandeira: </th>
                                <th>Numero:</th>
                                <th>Nome:</th>
                                <th>Codigo:</th>
                                <th>Validade:</th>
                                <th><a href="${pageContext.request.contextPath}/cartao/FormCadastrarCartao.jsp">Novo</a></th>
                            </tr>
                        </thead>

                        <%
                            int contCar = 0;
                            for (int i = 0; i < cliente.getCliCartao().size(); i++) {

                                if (cliente.getCliCartao().get(i).getEtdStatus().equals("ativo") && cliente.getCliCartao().get(i).getCarCliId() == cliente.getEtdId()) {
                                    contCar++;
                                }
                            }

                            for (int i = 0; i < cliente.getCliCartao().size(); i++) {

                                if (cliente.getCliCartao().get(i).getEtdStatus().equals("ativo") && cliente.getCliCartao().get(i).getCarCliId() == cliente.getEtdId()) {
                        %>
                        <tr>
                            <td><%= cliente.getCliCartao().get(i).getEtdId()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarIsPreferencial()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarBandeira()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarNumero()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarNomeImpresso()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarCodSeguranca()%></td>
                            <td><%= cliente.getCliCartao().get(i).getCarValidade()%></td>
                            <td><a href="${pageContext.request.contextPath}/ConsultarCartao?txtListaId=<%=cliente.getCliCartao().get(i).getEtdId()%>&OPERACAO=VISUALIZAR" class="btn btn-default" >Visualizar</a></td>
                            <%if (contCar > 1) {%>
                            <td><a href="${pageContext.request.contextPath}/ExcluirCartao?OPERACAO=EXCLUIR&txtListaId=<%=cliente.getCliCartao().get(i).getEtdId()%>" class="btn btn-default" >Excluir</a></td>
                            <%}%>
                        </tr>

                        <%
                                }
                            }
                        %>

                    </table>
                </div>
                </br>                
                <div class="container card-header">
                    <h3>Dados do Login</h3>
                </div>
                </br>

                <div class="row">
                    <div class="form-group col-4">
                        <label for="txtCadLgnEmail">Email: </label>
                        <input type="text" class="form-control" value="<%=cliente.getPesLogin().getLgnEmail()%>"id="txtCadLgnEmail" name="txtCadLgnEmail" maxlength="50" readonly>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCadLgnSenha">Senha: </label>
                        <input type="password" class="form-control" value="<%=cliente.getPesLogin().getLgnSenhaAntida()%>"id="txtCadLgnSenha" name="txtCadLgnSenha" maxlength="16" readonly>
                    </div>

                    <div class="form-group col-2">
                        <td><a href="${pageContext.request.contextPath}/ConsultarLogin?txtLoginId=<%=cliente.getPesLogin().getEtdId()%>&OPERACAO=VISUALIZAR" class="btn btn-default" >Alterar senha</a></td>
                    </div>
                </div>
                </br>
                <div>
                    <input type="submit" class="btn btn-success"id="OPERACAO" name="OPERACAO" value="ALTERAR"/>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR">Cancelar</a>
                </div>
            </form>
        </div>
    </body>
</html>

