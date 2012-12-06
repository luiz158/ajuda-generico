/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ajuda.generico.entities;

import br.ajuda.generico.jdbc.annotation.CampoBD;
import br.ajuda.generico.jdbc.annotation.Id;
import br.ajuda.generico.jdbc.annotation.Tabela;
import br.ajuda.generico.jdbc.annotation.Transitorio;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jacob
 */
@Tabela(nome="ag_tema",schema="public")
public class Tema implements Serializable {

    @Transitorio
    private static final long serialVersionUID = -8249014547569069393L;
    @Id
    @CampoBD(nome="id_tema")
    private Long idTema;
    @CampoBD(nome="titulo_tema",obrigatorio=true,mensagem="Preencha o campo Titulo!")
    private String tituloTema;
    @CampoBD(nome="descricao_tema")
    private String descricaoTema;
    @CampoBD(nome="data_insercao")
    private Date dataInsercao;
    @CampoBD(nome="data_atualizacao")
    private Date dataAtualizacao;

    public Tema() {
    }

    public Tema(Long idTema) {
        this.idTema = idTema;
    }

    public Tema(Long idTema, String tituloTema) {
        this.idTema = idTema;
        this.tituloTema = tituloTema;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getTituloTema() {
        return tituloTema;
    }

    public void setTituloTema(String tituloTema) {
        this.tituloTema = tituloTema;
    }

    public String getDescricaoTema() {
        return descricaoTema;
    }

    public void setDescricaoTema(String descricaoTema) {
        this.descricaoTema = descricaoTema;
    }

    public Date getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTema != null ? idTema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.idTema == null && other.idTema != null) || (this.idTema != null && !this.idTema.equals(other.idTema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ajuda.generico.entities.Tema[idTema=" + idTema + "]";
    }

}
