package dominio;

public class Cartao extends EntidadeDominio {

    private String carBandeira;
    private String carNumero;
    private String carNomeImpresso;
    private String carCodSeguranca;
    private String carValidade;
    private String carIsPreferencial;
    private int carCliId;

    public String getCarBandeira() {
        return carBandeira;
    }

    public void setCarBandeira(String carBandeira) {
        this.carBandeira = carBandeira;
    }

    public String getCarNumero() {
        return carNumero;
    }

    public void setCarNumero(String carNumero) {
        this.carNumero = carNumero;
    }

    public String getCarNomeImpresso() {
        return carNomeImpresso;
    }

    public void setCarNomeImpresso(String carNomeImpresso) {
        this.carNomeImpresso = carNomeImpresso;
    }

    public String getCarCodSeguranca() {
        return carCodSeguranca;
    }

    public void setCarCodSeguranca(String carCodSeguranca) {
        this.carCodSeguranca = carCodSeguranca;
    }

    public String getCarValidade() {
        return carValidade;
    }

    public void setCarValidade(String carValidade) {
        this.carValidade = carValidade;
    }

    public String getCarIsPreferencial() {
        return carIsPreferencial;
    }

    public void setCarIsPreferencial(String carIsPreferencial) {
        this.carIsPreferencial = carIsPreferencial;
    }

    public int getCarCliId() {
        return carCliId;
    }

    public void setCarCliId(int carCliId) {
        this.carCliId = carCliId;
    }

}
