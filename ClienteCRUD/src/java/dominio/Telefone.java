package dominio;

public class Telefone extends EntidadeDominio {

    private String telTipo;
    private String telDDD;
    private String telNumero;

    public String getTelTipo() {
        return telTipo;
    }

    public void setTelTipo(String telTipo) {
        this.telTipo = telTipo;
    }

    public String getTelDDD() {
        return telDDD;
    }

    public void setTelDDD(String telDDD) {
        this.telDDD = telDDD;
    }

    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
    }

}
