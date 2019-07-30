package dominio;

public class Login extends EntidadeDominio {

    private String lgnEmail;
    private String lgnSenhaAntida;
    private String lgnSenhaNova;
    private String lgnConfirmarSenha;
    private int lgnCliId;

    public String getLgnEmail() {
        return lgnEmail;
    }

    public void setLgnEmail(String lgnEmail) {
        this.lgnEmail = lgnEmail;
    }

    public String getLgnSenhaAntida() {
        return lgnSenhaAntida;
    }

    public void setLgnSenhaAntida(String lgnSenhaAntida) {
        this.lgnSenhaAntida = lgnSenhaAntida;
    }

    public String getLgnSenhaNova() {
        return lgnSenhaNova;
    }

    public void setLgnSenhaNova(String lgnSenhaNova) {
        this.lgnSenhaNova = lgnSenhaNova;
    }

    public String getLgnConfirmarSenha() {
        return lgnConfirmarSenha;
    }

    public void setLgnConfirmarSenha(String lgnConfirmarSenha) {
        this.lgnConfirmarSenha = lgnConfirmarSenha;
    }

    public int getLgnCliId() {
        return lgnCliId;
    }

    public void setLgnCliId(int lgnCliId) {
        this.lgnCliId = lgnCliId;
    }

}
