package dominio;

import java.util.List;

public class Cliente extends Pessoa {

    private List<Cartao> cliCartao;
    private String cliCPF;
    private int cliRank;
    private String cliPesquisa;

    public List<Cartao> getCliCartao() {
        return cliCartao;
    }

    public void setCliCartao(List<Cartao> cliCartao) {
        this.cliCartao = cliCartao;
    }

    public String getCliCPF() {
        return cliCPF;
    }

    public void setCliCPF(String cliCPF) {
        this.cliCPF = cliCPF;
    }

    public int getCliRank() {
        return cliRank;
    }

    public void setCliRank(int cliRank) {
        this.cliRank = cliRank;
    }

    public String getCliPesquisa() {
        return cliPesquisa;
    }

    public void setCliPesquisa(String cliPesquisa) {
        this.cliPesquisa = cliPesquisa;
    }

}
