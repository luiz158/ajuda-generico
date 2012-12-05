/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc.annotation;

import java.io.Serializable;

/**
 *
 * @author jacob_lisboa
 */
public class CampoDto implements Serializable {

    private String nome;
    private String mensagem;    

    public CampoDto(String nome, String mensagem) {
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "[Nome Campo:" +nome+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CampoDto other = (CampoDto) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.mensagem == null) ? (other.mensagem != null) : !this.mensagem.equals(other.mensagem)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 41 * hash + (this.mensagem != null ? this.mensagem.hashCode() : 0);
        return hash;
    }

    
}
