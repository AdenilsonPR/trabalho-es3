package dominio;

import java.util.List;

public class Pessoa extends EntidadeDominio {

    private String pesNome;
    private String pesDataNascimento;
    private Login pesLogin;
    private List<Endereco> pesEndereco;
    private Telefone pesTelefone;
    private String pesGenero;

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public String getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(String pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public Login getPesLogin() {
        return pesLogin;
    }

    public void setPesLogin(Login pesLogin) {
        this.pesLogin = pesLogin;
    }

    public List<Endereco> getPesEndereco() {
        return pesEndereco;
    }

    public void setPesEndereco(List<Endereco> pesEndereco) {
        this.pesEndereco = pesEndereco;
    }

    public Telefone getPesTelefone() {
        return pesTelefone;
    }

    public void setPesTelefone(Telefone pesTelefone) {
        this.pesTelefone = pesTelefone;
    }

    public String getPesGenero() {
        return pesGenero;
    }

    public void setPesGenero(String pesGenero) {
        this.pesGenero = pesGenero;
    }

}
