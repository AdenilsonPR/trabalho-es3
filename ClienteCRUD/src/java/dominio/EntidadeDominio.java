package dominio;

import java.util.Date;

public class EntidadeDominio implements IEntidade {

    private int etdId;
    private Date etdDataCadastro;
    private String etdStatus;

    public int getEtdId() {
        return etdId;
    }

    public void setEtdId(int etdId) {
        this.etdId = etdId;
    }

    public Date getEtdDataCadastro() {
        return etdDataCadastro;
    }

    public void setEtdDataCadastro(Date etdDataCadastro) {
        this.etdDataCadastro = etdDataCadastro;
    }

    public String getEtdStatus() {
        return etdStatus;
    }

    public void setEtdStatus(String etdStatus) {
        this.etdStatus = etdStatus;
    }

}
