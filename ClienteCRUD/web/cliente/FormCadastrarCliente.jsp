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
            Resultado clientes = (Resultado) session.getAttribute("clienteSessao");

            String nome = "";
            String genero = "";
            String cpf = "";
            String nascimento = "";
            String tipoTel = "";
            String ddd = "";
            String numeroTel = "";
            String tipoEnd = "";
            String tipoLogradouroEnd = "";
            String logradouro = "";
            String numeroEnd = "";
            String pais = "";
            String cep = "";
            String estado = "";
            String cidade = "";
            String bairro = "";
            String bandeira = "";
            String numeroCar = "";
            String nomeCar = "";
            String codigoCar = "";
            String validadeCar = "";
            String email = "";

            if (resultado != null) {
                if (resultado.getEntidades() != null) {
                    Cliente cliente = (Cliente) clientes.getEntidades().get(0);

                    if (cliente.getPesNome() == null) {
                        nome = "";
                    } else {
                        nome = cliente.getPesNome();
                    }

                    if (cliente.getPesGenero() == null) {
                        genero = "";
                    } else {
                        genero = cliente.getPesGenero();
                    }

                    if (cliente.getCliCPF() == null) {
                        cpf = "";
                    } else {
                        cpf = cliente.getCliCPF();
                    }

                    if (cliente.getPesDataNascimento() == null) {
                        nascimento = "";
                    } else {
                        nascimento = cliente.getPesDataNascimento();
                    }

                    if (cliente.getPesTelefone().getTelTipo() == null) {
                        tipoTel = "";
                    } else {
                        tipoTel = cliente.getPesTelefone().getTelTipo();
                    }

                    if (cliente.getPesTelefone().getTelDDD() == null) {
                        ddd = "";
                    } else {
                        ddd = cliente.getPesTelefone().getTelDDD();
                    }

                    if (cliente.getPesTelefone().getTelNumero() == null) {
                        numeroTel = "";
                    } else {
                        numeroTel = cliente.getPesTelefone().getTelNumero();
                    }

                    if (cliente.getPesEndereco().get(0).getEndTipo() == null) {
                        tipoEnd = "";
                    } else {
                        tipoEnd = cliente.getPesEndereco().get(0).getEndTipo();
                    }

                    if (cliente.getPesEndereco().get(0).getEndTipoLogradouro() == null) {
                        tipoLogradouroEnd = "";
                    } else {
                        tipoLogradouroEnd = cliente.getPesEndereco().get(0).getEndTipoLogradouro();
                    }

                    if (cliente.getPesEndereco().get(0).getEndLogradouro() == null) {
                        logradouro = "";
                    } else {
                        logradouro = cliente.getPesEndereco().get(0).getEndLogradouro();
                    }

                    if (cliente.getPesEndereco().get(0).getEndNumero() == null) {
                        numeroEnd = "";
                    } else {
                        numeroEnd = cliente.getPesEndereco().get(0).getEndNumero();
                    }

                    if (cliente.getPesEndereco().get(0).getEndPais() == null) {
                        pais = "";
                    } else {
                        pais = cliente.getPesEndereco().get(0).getEndPais();
                    }

                    if (cliente.getPesEndereco().get(0).getEndCEP() == null) {
                        cep = "";
                    } else {
                        cep = cliente.getPesEndereco().get(0).getEndCEP();
                    }
                    if (cliente.getPesEndereco().get(0).getEndEstado() == null) {
                        estado = "";
                    } else {
                        estado = cliente.getPesEndereco().get(0).getEndEstado();
                    }

                    if (cliente.getPesEndereco().get(0).getEndCidade() == null) {
                        cidade = "";
                    } else {
                        cidade = cliente.getPesEndereco().get(0).getEndCidade();
                    }

                    if (cliente.getPesEndereco().get(0).getEndBairro() == null) {
                        bairro = "";
                    } else {
                        bairro = cliente.getPesEndereco().get(0).getEndBairro();
                    }

                    if (cliente.getCliCartao().get(0).getCarBandeira() == null) {
                        bandeira = "";
                    } else {
                        bandeira = cliente.getCliCartao().get(0).getCarBandeira();
                    }

                    if (cliente.getCliCartao().get(0).getCarNumero() == null) {
                        numeroCar = "";
                    } else {
                        numeroCar = cliente.getCliCartao().get(0).getCarNumero();
                    }

                    if (cliente.getCliCartao().get(0).getCarNomeImpresso() == null) {
                        nomeCar = "";
                    } else {
                        nomeCar = cliente.getCliCartao().get(0).getCarNomeImpresso();
                    }

                    if (cliente.getCliCartao().get(0).getCarCodSeguranca() == null) {
                        codigoCar = "";
                    } else {
                        codigoCar = cliente.getCliCartao().get(0).getCarCodSeguranca();
                    }

                    if (cliente.getCliCartao().get(0).getCarValidade() == null) {
                        validadeCar = "";
                    } else {
                        validadeCar = cliente.getCliCartao().get(0).getCarValidade();
                    }

                    if (cliente.getPesLogin().getLgnEmail() == null) {
                        email = "";
                    } else {
                        email = cliente.getPesLogin().getLgnEmail();
                    }
                }
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/ConsultarCliente?OPERACAO=CONSULTAR" class="btn btn-default">Lista</a>
                        <!--<a class="nav-link" href="${pageContext.request.contextPath}/cliente/FormListarCliente.jsp">Lista</a>-->
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/cliente/FormCadastrarCliente.jsp">Cadastrar</a>
                    </li>
                </ul>
            </div>
        </nav>
        </br>
        <div class="container">
            <form action="${pageContext.request.contextPath}/SalvarCliente">

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
                    <div class="form-group col-6">
                        <label for="txtNome">Nome: </label>
                        <input type="text" class="form-control" value="<%= nome%>" id="txtNome" name="txtNome" placeholder="Nome completo" maxlength="30">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtGenero">Genero: </label>
                        <select class="form-control" id="txtGenero" name="txtGenero">
                            <option value="Feminino" <%= genero.equals("Feminino") ? "selected" : " "%>>Feminino</option>
                            <option value="Masculino" <%= genero.equals("Masculino") ? "selected" : " "%>>Masculino</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCPF">CPF: </label>
                        <input type="text" class="form-control" value="<%= cpf%>"id="txtCPF" name="txtCPF" onkeypress=" mascara(this, '###.###.###-##'); return SomenteNumero(event)" placeholder="000.000.000-00" maxlength="14">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtDataNascimento">Data de nascimento: </label>
                        <input type="text" class="form-control" value="<%= nascimento%>"id="txtDataNascimento" name="txtDataNascimento" onkeypress="mascara(this, '##/##/####'); return SomenteNumero(event)" placeholder="dd/mm/aaaa" maxlength="10">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-2">
                        <label for="txtTelTipo">Tipo de Telefone: </label>
                        <select class="form-control" id="txtTelTipo" name="txtTelTipo">
                            <option value="Fixo" <%= tipoTel.equals("Fixo") ? "selected" : " "%>>Fixo</option>
                            <option value="Celular" <%= tipoTel.equals("Celular") ? "selected" : " "%>>Celular</option>
                        </select>
                    </div>

                    <div class="form-group col-1">
                        <label for="txtTelDDD">DDD: </label>
                        <select class="form-control" id="txtTelDDD" name="txtTelDDD">
                            <option value="11"<%= ddd.equals("11") ? "selected" : " "%>>11</option>
                            <option value="12"<%= ddd.equals("12") ? "selected" : " "%>>12</option>
                            <option value="13"<%= ddd.equals("13") ? "selected" : " "%>>13</option>
                            <option value="14"<%= ddd.equals("14") ? "selected" : " "%>>14</option>
                            <option value="15"<%= ddd.equals("15") ? "selected" : " "%>>15</option>
                            <option value="16"<%= ddd.equals("16") ? "selected" : " "%>>16</option>
                            <option value="17"<%= ddd.equals("17") ? "selected" : " "%>>17</option>
                            <option value="18"<%= ddd.equals("18") ? "selected" : " "%>>18</option>
                            <option value="19"<%= ddd.equals("19") ? "selected" : " "%>>19</option>
                            <option value="20"<%= ddd.equals("20") ? "selected" : " "%>>20</option>
                        </select>
                    </div>

                    <div class="form-group col-2">
                        <label for="txtTelNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= numeroTel%>" id="txtTelNumero" name="txtTelNumero" onkeypress="mascara(this, '#####-####'); return SomenteNumero(event)" placeholder="00000-0000" maxlength="10">
                    </div>
                </div>

                </br>                
                <div class="container card-header">
                    <h3>Dados do Endereço</h3>
                </div>

                <div class="row">
                    <input type="hidden" id="txtEndCobranca" name="txtEndCobranca" value="Sim">
                    <input type="hidden" id="txtEndEntrega" name="txtEndEntrega" value="Sim">
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

                    <div class="form-group col-2">
                        <label for="txtEndNumero">Numero: </label>
                        <input type="text" class="form-control" value="<%= numeroEnd%>" id="txtEndNumero" name="txtEndNumero" placeholder="00000" maxlength="10">
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

                </br>                
                <div class="container card-header">
                    <h3>Dados do Cartão</h3>
                </div>
                </br>                

                <div class="row">
                    <div class="form-group col-2">
                        <input type="hidden" id="txtCarPreferencial" name="txtCarPreferencial" value="Sim">
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

                    <div class="form-group col-4">
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

                </br>                
                <div class="container card-header">
                    <h3>Dados do Login</h3>
                </div>
                </br>

                <div class="row">
                    <div class="form-group col-4">
                        <label for="txtCadLgnEmail">Email: </label>
                        <input type="text" class="form-control" value="<%= email%>" id="txtCadLgnEmail" name="txtCadLgnEmail" placeholder="email@email.com" maxlength="50">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCadLgnSenha">Senha: </label>
                        <input type="password" class="form-control" id="txtCadLgnSenha" name="txtCadLgnSenha" maxlength="8">
                    </div>

                    <div class="form-group col-2">
                        <label for="txtCadLgnConSenha">Confirmar senha: </label>
                        <input type="password" class="form-control" id="txtCadLgnConSenha" name="txtCadLgnConSenha" maxlength="8">
                    </div>
                </div>
                </br>
                <div>
                    <input type="submit" class="btn btn-success"id="OPERACAO" name="OPERACAO" value="SALVAR"/>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/ConsultarCliente?txtPesquisa=ativo&OPERACAO=CONSULTAR">Cancelar</a>
                </div>
            </form>
        </div>
    </body>
</html>

