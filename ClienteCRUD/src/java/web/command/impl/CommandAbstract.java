package web.command.impl;

import core.impl.controle.impl.Fachada;
import web.command.ICommand;
import core.impl.controle.IFachada;

public abstract class CommandAbstract implements ICommand {

    protected IFachada fachada = new Fachada();
}
