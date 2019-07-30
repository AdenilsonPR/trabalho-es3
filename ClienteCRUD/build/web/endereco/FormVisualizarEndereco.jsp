
<%@page import="core.aplicacao.Resultado"%>
<%@page import="dominio.Endereco"%>
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
            Endereco endereco = (Endereco) session.getAttribute("VisualizarEndereco");
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
        <div class="container">

            <div class="container card-header">
                <h3>Dados do Endereço</h3>
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
            <form action="${pageContext.request.contextPath}/AlterarEndereco">

                <div class="row">
                    <input type="hidden" class="form-control" value="<%= endereco.getEndCliId()%>" id="txtEndCliId" name="txtEndCliId">
                    <div class="form-group col-1">
                        <label for="txtAlterarEnderecoId">Id: </label>
                        <input type="text" class="form-control" value="<%= endereco.getEtdId()%>" id="txtAlterarEnderecoId" name="txtAlterarEnderecoId" readonly>
                    </div>
                    <div class="form-group col-2">
                        <label for="txtEndCEP">CEP: </label>
                        <input type="text" class="form-control"  value="<%= endereco.getEndCEP()%>" id="txtEndCEP" name="txtEndCEP" onkeypress="mascara(this, '#####-###'); return SomenteNumero(event)" placeholder="00000-000" maxlength="9">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndTipo">Tipo endereço: </label>
                        <select class="form-control" id="txtEndTipo" name="txtEndTipo">
                            <option value="Casa" <%= endereco.getEndTipo().equals("Casa") ? "selected" : " "%>>Casa</option>
                            <option value="Condominio" <%= endereco.getEndTipo().equals("Condominio") ? "selected" : " "%>>Condominio</option>
                            <option value="Apartamento" <%= endereco.getEndTipo().equals("Apartamento") ? "selected" : " "%>>Apartamento</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndTipoLogradouro">Tipo logradouro: </label>
                        <select class="form-control" id="txtEndTipoLogradouro" name="txtEndTipoLogradouro">
                            <option value="Rua" <%= endereco.getEndTipoLogradouro().equals("Rua") ? "selected" : " "%>>Rua</option>
                            <option value="Avenida" <%= endereco.getEndTipoLogradouro().equals("Avenida") ? "selected" : " "%>>Avenida</option>
                            <option value="Bloco" <%= endereco.getEndTipoLogradouro().equals("Bloco") ? "selected" : " "%>>Bloco</option>
                            <option value="Viela" <%= endereco.getEndTipoLogradouro().equals("Viela") ? "selected" : " "%>>Viela</option>
                        </select>

                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndLogradouro">Logradouro: </label>
                        <input type="text" class="form-control" value="<%= endereco.getEndLogradouro()%>" id="txtEndLogradouro" name="txtEndLogradouro" placeholder="Logradrouro" maxlength="30">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtEndNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= endereco.getEndNumero()%>" id="txtEndNumero" name="txtEndNumero" placeholder="00000" maxlength="10">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndCobranca">Cobrança: </label>
                        <select class="form-control" id="txtEndCobranca" name="txtEndCobranca">
                            <option  value="Sim" <%= endereco.getEndIsCobranca().equals("Sim") ? "selected" : " "%>>Sim</option>
                            <option  value="Não" <%= endereco.getEndIsCobranca().equals("Não") ? "selected" : " "%>>Não</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndEntrega">Entrega: </label>
                        <select class="form-control" id="txtEndEntrega" name="txtEndEntrega">
                            <option  value="Sim" <%= endereco.getEndIsEntrega().equals("Sim") ? "selected" : " "%>>Sim</option>
                            <option  value="Não" <%= endereco.getEndIsEntrega().equals("Não") ? "selected" : " "%>>Não</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtEndPais">Pais: </label>
                        <select class="form-control" id="txtEndPais" name="txtEndPais">
                            <option value="Brasil" <%= endereco.getEndPais().equals("Brasil") ? "selected" : " "%>>Brasil</option>
                            <option value="Japão" <%= endereco.getEndPais().equals("Japão") ? "selected" : " "%>>Japão</option>
                            <option value="EUA" <%= endereco.getEndPais().equals("EUA") ? "selected" : " "%>>EUA</option>
                            <option value="Canadá" <%= endereco.getEndPais().equals("Canadá") ? "selected" : " "%>>Canadá</option>
                            <option value="China" <%= endereco.getEndPais().equals("China") ? "selected" : " "%>>China</option>
                            <option value="Argentina" <%= endereco.getEndPais().equals("Argentina") ? "selected" : " "%>>Argentina</option>
                            <option value="Peru" <%= endereco.getEndPais().equals("Peru") ? "selected" : " "%>>Peru</option>
                            <option value="Paraguai" <%= endereco.getEndPais().equals("Paraguai") ? "selected" : " "%>>Paraguai</option>
                            <option value="Russia" <%= endereco.getEndPais().equals("Russia") ? "selected" : " "%>>Russia</option>
                            <option value="Alemanha" <%= endereco.getEndPais().equals("Alemanha") ? "selected" : " "%>>Alemanha</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndEstado">Estado: </label>
                        <select class="form-control" id="txtEndEstado" name="txtEndEstado">
                            <option value="São Paulo" <%= endereco.getEndEstado().equals("São Paulo") ? "selected" : " "%>>São Paulo</option>
                            <option value="Rio de Janeiro" <%= endereco.getEndEstado().equals("Rio de Janeiro") ? "selected" : " "%>>Rio de Janeiro</option>
                            <option value="Acre" <%= endereco.getEndEstado().equals("Acre") ? "selected" : " "%>>Acre</option>
                            <option value="Goiás" <%= endereco.getEndEstado().equals("Goiás") ? "selected" : " "%>>Goiás</option>
                            <option value="Pará" <%= endereco.getEndEstado().equals("Pará") ? "selected" : " "%>>Pará</option>
                            <option value="Paraíba" <%= endereco.getEndEstado().equals("Paraíba") ? "selected" : " "%>>Paraíba</option>
                            <option value="Rio Grande do Sul" <%= endereco.getEndEstado().equals("Rio Grande do Sul") ? "selected" : " "%>>Rio Grande do Sul</option>
                            <option value="Rio Grande do Norte" <%= endereco.getEndEstado().equals("Rio Grande do Norte") ? "selected" : " "%>>Rio Grande do Norte</option>
                            <option value="Santa Catarina" <%= endereco.getEndEstado().equals("Santa Catarina") ? "selected" : " "%>>Santa Catarina</option>
                            <option value="Sergipe" <%= endereco.getEndEstado().equals("Sergipe") ? "selected" : " "%>>Sergipe</option>
                        </select>
                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndCidade">Cidade: </label>
                        <input type="text" class="form-control" value="<%= endereco.getEndCidade()%>" id="txtEndCidade" name="txtEndCidade" placeholder="Cidade" maxlength="30">
                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndBairro">Bairro: </label>
                        <input type="text" class="form-control" value="<%= endereco.getEndBairro()%>" id="txtEndBairro" name="txtEndBairro" placeholder="Bairro" maxlength="30">
                    </div>
                </div>
                <div>
                    <input type="submit" class="btn btn-success"id="OPERACAO" name="OPERACAO" value="ALTERAR"/>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR">Cancelar</a>
                </div>
            </form>
        </div>
    </body>
</html>