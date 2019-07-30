package web.viewHelper;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request);

    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
