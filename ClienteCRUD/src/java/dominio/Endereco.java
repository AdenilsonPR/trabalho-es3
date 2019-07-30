package dominio;

public class Endereco extends EntidadeDominio {

    private String endTipo;
    private String endLogradouro;
    private String endTipoLogradouro;
    private String endNumero;
    private String endBairro;
    private String endCEP;
    private String endCidade;
    private String endEstado;
    private String endPais;
    private String endIsCobranca;
    private String endIsEntrega;
    private int endCliId;

    public String getEndTipo() {
        return endTipo;
    }

    public void setEndTipo(String endTipo) {
        this.endTipo = endTipo;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndCEP() {
        return endCEP;
    }

    public void setEndCEP(String endCEP) {
        this.endCEP = endCEP;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndEstado() {
        return endEstado;
    }

    public void setEndEstado(String endEstado) {
        this.endEstado = endEstado;
    }

    public String getEndPais() {
        return endPais;
    }

    public void setEndPais(String endPais) {
        this.endPais = endPais;
    }

    public String getEndIsCobranca() {
        return endIsCobranca;
    }

    public void setEndIsCobranca(String endIsCobranca) {
        this.endIsCobranca = endIsCobranca;
    }

    public String getEndIsEntrega() {
        return endIsEntrega;
    }

    public void setEndIsEntrega(String endIsEntrega) {
        this.endIsEntrega = endIsEntrega;
    }

    public int getEndCliId() {
        return endCliId;
    }

    public void setEndCliId(int endCliId) {
        this.endCliId = endCliId;
    }

}
