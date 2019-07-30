<%@page import="dominio.Endereco"%>
<%@page import="dominio.Cliente"%>
<%@page import="core.aplicacao.Resultado"%>
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
            Resultado enderecos = (Resultado) session.getAttribute("enderecoSessao");
            Cliente cliente = (Cliente) session.getAttribute("VisualizarCliente");

            int id = cliente.getEtdId();

            String tipoEnd = "";
            String tipoLogradouroEnd = "";
            String logradouro = "";
            String numeroEnd = "";
            String pais = "";
            String cep = "";
            String estado = "";
            String cidade = "";
            String bairro = "";
            String cobranca = "";
            String entrega = "";

            if (resultado != null) {
                if (resultado.getEntidades() != null) {
                    Endereco endereco = (Endereco) enderecos.getEntidades().get(0);

                    if (endereco.getEndTipo() == null) {
                        tipoEnd = "";
                    } else {
                        tipoEnd = endereco.getEndTipo();
                    }

                    if (endereco.getEndTipoLogradouro() == null) {
                        tipoLogradouroEnd = "";
                    } else {
                        tipoLogradouroEnd = endereco.getEndTipoLogradouro();
                    }

                    if (endereco.getEndLogradouro() == null) {
                        logradouro = "";
                    } else {
                        logradouro = endereco.getEndLogradouro();
                    }

                    if (endereco.getEndNumero() == null) {
                        numeroEnd = "";
                    } else {
                        numeroEnd = endereco.getEndNumero();
                    }

                    if (endereco.getEndPais() == null) {
                        pais = "";
                    } else {
                        pais = endereco.getEndPais();
                    }

                    if (endereco.getEndCEP() == null) {
                        cep = "";
                    } else {
                        cep = endereco.getEndCEP();
                    }

                    if (endereco.getEndEstado() == null) {
                        estado = "";
                    } else {
                        estado = endereco.getEndEstado();
                    }

                    if (endereco.getEndCidade() == null) {
                        cidade = "";
                    } else {
                        cidade = endereco.getEndCidade();
                    }

                    if (endereco.getEndBairro() == null) {
                        bairro = "";
                    } else {
                        bairro = endereco.getEndBairro();
                    }

                    if (endereco.getEndIsCobranca() == null) {
                        cobranca = "";
                    } else {
                        cobranca = endereco.getEndIsCobranca();
                    }

                    if (endereco.getEndIsEntrega() == null) {
                        entrega = "";
                    } else {
                        entrega = endereco.getEndIsEntrega();
                    }
                }
            }
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
            <form action="${pageContext.request.contextPath}/SalvarEndereco">

                <div class="row">

                    <input type="hidden" class="form-control" value="<%= id%>" id="txtEndCliId" name="txtEndCliId">

                    <div class="form-group col-2">
                        <label for="txtEndCEP">CEP: </label>
                        <input type="text" class="form-control"  value="<%= cep%>" id="txtEndCEP" name="txtEndCEP" onkeypress="mascara(this, '#####-###'); return SomenteNumero(event)" placeholder="00000-000" maxlength="9">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndTipo">Tipo endereço: </label>
                        <select class="form-control" id="txtEndTipo" name="txtEndTipo">
                            <option value="Casa" <%= tipoEnd.equals("Casa") ? "selected" : " "%>>Casa</option>
                            <option value="Condominio" <%= tipoEnd.equals("Condominio") ? "selected" : " "%>>Condominio</option>
                            <option value="Apartamento" <%= tipoEnd.equals("Apartamento") ? "selected" : " "%>>Apartamento</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndTipoLogradouro">Tipo logradouro: </label>
                        <select class="form-control" id="txtEndTipoLogradouro" name="txtEndTipoLogradouro">
                            <option value="Rua" <%= tipoLogradouroEnd.equals("Rua") ? "selected" : " "%>>Rua</option>
                            <option value="Avenida" <%= tipoLogradouroEnd.equals("Avenida") ? "selected" : " "%>>Avenida</option>
                            <option value="Bloco" <%= tipoLogradouroEnd.equals("Bloco") ? "selected" : " "%>>Bloco</option>
                            <option value="Viela" <%= tipoLogradouroEnd.equals("Viela") ? "selected" : " "%>>Viela</option>
                        </select>

                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndLogradouro">Logradouro: </label>
                        <input type="text" class="form-control" value="<%= logradouro%>" id="txtEndLogradouro" name="txtEndLogradouro" placeholder="Logradrouro" maxlength="30">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtEndNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= numeroEnd%>" id="txtEndNumero" name="txtEndNumero" placeholder="00000" maxlength="10">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndCobranca">Cobrança: </label>
                        <select class="form-control" id="txtEndCobranca" name="txtEndCobranca">
                            <option  value="Não" <%= cobranca.equals("Não") ? "selected" : " "%>>Não</option>
                            <option  value="Sim" <%= cobranca.equals("Sim") ? "selected" : " "%>>Sim</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndEntrega">Entrega: </label>
                        <select class="form-control" id="txtEndEntrega" name="txtEndEntrega">
                            <option  value="Não" <%= entrega.equals("Não") ? "selected" : " "%>>Não</option>
                            <option  value="Sim" <%= entrega.equals("Sim") ? "selected" : " "%>>Sim</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtEndPais">Pais: </label>
                        <select class="form-control" id="txtEndPais" name="txtEndPais">
                            <option value="Brasil" <%= pais.equals("Brasil") ? "selected" : " "%>>Brasil</option>
                            <option value="Japão" <%= pais.equals("Japão") ? "selected" : " "%>>Japão</option>
                            <option value="EUA" <%= pais.equals("EUA") ? "selected" : " "%>>EUA</option>
                            <option value="Canadá" <%= pais.equals("Canadá") ? "selected" : " "%>>Canadá</option>
                            <option value="China" <%= pais.equals("China") ? "selected" : " "%>>China</option>
                            <option value="Argentina" <%= pais.equals("Argentina") ? "selected" : " "%>>Argentina</option>
                            <option value="Peru" <%= pais.equals("Peru") ? "selected" : " "%>>Peru</option>
                            <option value="Paraguai" <%= pais.equals("Paraguai") ? "selected" : " "%>>Paraguai</option>
                            <option value="Russia" <%= pais.equals("Russia") ? "selected" : " "%>>Russia</option>
                            <option value="Alemanha" <%= pais.equals("Alemanha") ? "selected" : " "%>>Alemanha</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtEndEstado">Estado: </label>
                        <select class="form-control" id="txtEndEstado" name="txtEndEstado">
                            <option value="São Paulo" <%= estado.equals("São Paulo") ? "selected" : " "%>>São Paulo</option>
                            <option value="Rio de Janeiro" <%= estado.equals("Rio de Janeiro") ? "selected" : " "%>>Rio de Janeiro</option>
                            <option value="Acre" <%= estado.equals("Acre") ? "selected" : " "%>>Acre</option>
                            <option value="Goiás" <%= estado.equals("Goiás") ? "selected" : " "%>>Goiás</option>
                            <option value="Pará" <%= estado.equals("Pará") ? "selected" : " "%>>Pará</option>
                            <option value="Paraíba" <%= estado.equals("Paraíba") ? "selected" : " "%>>Paraíba</option>
                            <option value="Rio Grande do Sul" <%= estado.equals("Rio Grande do Sul") ? "selected" : " "%>>Rio Grande do Sul</option>
                            <option value="Rio Grande do Norte" <%= estado.equals("Rio Grande do Norte") ? "selected" : " "%>>Rio Grande do Norte</option>
                            <option value="Santa Catarina" <%= estado.equals("Santa Catarina") ? "selected" : " "%>>Santa Catarina</option>
                            <option value="Sergipe" <%= estado.equals("Sergipe") ? "selected" : " "%>>Sergipe</option>
                        </select>
                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndCidade">Cidade: </label>
                        <input type="text" class="form-control" value="<%= cidade%>" id="txtEndCidade" name="txtEndCidade" placeholder="Cidade" maxlength="30">
                    </div>

                    <div class="form-group col-4">
                        <label for="txtEndBairro">Bairro: </label>
                        <input type="text" class="form-control" value="<%= bairro%>" id="txtEndBairro" name="txtEndBairro" placeholder="Bairro" maxlength="30">
                    </div>
                </div>
                <div>
                    <input type="submit" class="btn btn-success"id="OPERACAO" name="OPERACAO" value="SALVAR"/>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR">Cancelar</a>
                </div>
            </form>
        </div>
    </body>
</html>
