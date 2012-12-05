/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacob_lisboa
 */
public class CampoObrigException extends Exception {

    private List<CampoDto> campos = new ArrayList();

    public List<CampoDto> getList() {
        return campos;
    }

    public CampoObrigException(Throwable cause) {
        super(cause);
    }

    public CampoObrigException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampoObrigException(String nome, String mensagem) {
        super(mensagem);
        campos.add(new CampoDto(nome, mensagem));
    }

    public CampoObrigException() {
        super();
    }

    public void adic(String nome,String mensagem){
        campos.add(new CampoDto(nome, mensagem));
    }

    public List<CampoDto> getCampos() {
        return campos;
    }
}
