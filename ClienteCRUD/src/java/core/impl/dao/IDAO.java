package core.impl.dao;

import dominio.EntidadeDominio;
import java.util.List;

public interface IDAO {

    public void salvar(EntidadeDominio entidade);

    public void alterar(EntidadeDominio entidade);

    public void excluir(EntidadeDominio entidade);

    public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
