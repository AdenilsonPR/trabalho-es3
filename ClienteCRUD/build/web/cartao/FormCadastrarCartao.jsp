
<%@page import="dominio.Cliente"%>
<%@page import="core.aplicacao.Resultado"%>
<%@page import="dominio.Cartao"%>
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
            Resultado cartoes = (Resultado) session.getAttribute("cartaoSessao");
            Cliente cliente = (Cliente) session.getAttribute("VisualizarCliente");

            int id = cliente.getEtdId();

            String bandeira = "";
            String numeroCar = "";
            String nomeCar = "";
            String codigoCar = "";
            String validadeCar = "";
            String preferencial = "";

            if (resultado != null) {
                if (resultado.getEntidades() != null) {
                    Cartao cartao = (Cartao) cartoes.getEntidades().get(0);

                    if (cartao.getCarBandeira() == null) {
                        bandeira = "";
                    } else {
                        bandeira = cartao.getCarBandeira();
                    }

                    if (cartao.getCarNumero() == null) {
                        numeroCar = "";
                    } else {
                        numeroCar = cartao.getCarNumero();
                    }

                    if (cartao.getCarNomeImpresso() == null) {
                        nomeCar = "";
                    } else {
                        nomeCar = cartao.getCarNomeImpresso();
                    }

                    if (cartao.getCarCodSeguranca() == null) {
                        codigoCar = "";
                    } else {
                        codigoCar = cartao.getCarCodSeguranca();
                    }

                    if (cartao.getCarValidade() == null) {
                        validadeCar = "";
                    } else {
                        validadeCar = cartao.getCarValidade();
                    }

                    if (cartao.getCarIsPreferencial() == null) {
                        preferencial = "";
                    } else {
                        preferencial = cartao.getCarIsPreferencial();
                    }
                }
            }
        %>
        <div class="container">

            <div class="container card-header">
                <h3>Dados do Cartão</h3>
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
            <form action="${pageContext.request.contextPath}/SalvarCartao">
                <div class="row">

                    <input type="hidden" class="form-control" value="<%= id%>" id="txtCarCliId" name="txtCarCliId">

                    <div class="form-group col-2">
                        <label for="txtCarBandeira">Bandeira: </label>
                        <select class="form-control" id="txtCarBandeira" name="txtCarBandeira">
                            <option value="Visa" <%= bandeira.equals("Visa") ? "selected" : " "%>>Visa</option>
                            <option value="Mastercard" <%= bandeira.equals("Mastercard") ? "selected" : " "%>>Mastercard</option>
                            <option value="American Express" <%= bandeira.equals("American Express") ? "selected" : " "%>>American Express</option>
                            <option value="Elo" <%= bandeira.equals("Elo") ? "selected" : " "%>>Elo</option>
                            <option value="Discover Network" <%= bandeira.equals("Discover Network") ? "selected" : " "%>>Discover Network</option>
                        </select>
                    </div>

                    <div class="form-group col-3">
                        <label for="txtCarNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= numeroCar%>" id="txtCarNumero" name="txtCarNumero" onkeypress="mascara(this, '#### #### #### ####'); return SomenteNumero(event)" placeholder="0000 0000 0000 0000" maxlength="19">
                    </div>
                    <div class="form-group col-2">
                        <label for="txtCarPreferencial">Preferencial: </label>
                        <select class="form-control" id="txtCarPreferencial" name="txtCarPreferencial">
                            <option  value="Não" <%= preferencial.equals("Não") ? "selected" : " "%>>Não</option>
                            <option  value="Sim" <%= preferencial.equals("Sim") ? "selected" : " "%>>Sim</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-6">
                        <label for="txtCarNomeImpresso">Nome: </label>
                        <input type="text" class="form-control" value="<%= nomeCar%>" id="txtCarNomeImpresso" name="txtCarNomeImpresso" placeholder="Nome Impresso" maxlength="30">
                    </div>

                    <div class="form-group col-1">
                        <label for="txtCarCodSeguranca">Codigo: </label>
                        <input type="text" class="form-control" value="<%= codigoCar%>" id="txtCarCodSeguranca" name="txtCarCodSeguranca" placeholder="000" maxlength="3">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCarValidade">Validade: </label>
                        <input type="text" class="form-control" value="<%= validadeCar%>" id="txtCarValidade" name="txtCarValidade" onkeypress="mascara(this, '##/##'); return SomenteNumero(event)" placeholder="00/00" maxlength="5">
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
