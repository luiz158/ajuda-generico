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
@Tabela(nome="ag_subtema",schema="public")
public class SubTema implements Serializable {

    @Transitorio
    private static final long serialVersionUID = -8249014547569069393L;
    @Id
    @CampoBD(nome="id_subtema")
    private Long idSubTema;
    @CampoBD(nome="titulo_subtema",obrigatorio=true,mensagem="Preencha o titulo do subtema!")
    private String tituloSubTema;
    @CampoBD(nome="descricao_subtema")
    private String descricaoSubTema;
    @CampoBD(nome="data_insercao")
    private Date dataInsercao;
    @CampoBD(nome="data_atualizacao")
    private Date dataAtualizacao;
    @CampoBD(nome="id_tema")
    private Long idTema;
    @Transitorio
    private Tema tema;

    public SubTema() {
    }

    /**
     * @return the tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    /**
     * @return the idSubTema
     */
    public Long getIdSubTema() {
        return idSubTema;
    }

    /**
     * @param idSubTema the idSubTema to set
     */
    public void setIdSubTema(Long idSubTema) {
        this.idSubTema = idSubTema;
    }

    /**
     * @return the tituloSubTema
     */
    public String getTituloSubTema() {
        return tituloSubTema;
    }

    /**
     * @param tituloSubTema the tituloSubTema to set
     */
    public void setTituloSubTema(String tituloSubTema) {
        this.tituloSubTema = tituloSubTema;
    }

    /**
     * @return the descricaoSubTema
     */
    public String getDescricaoSubTema() {
        return descricaoSubTema;
    }

    /**
     * @param descricaoSubTema the descricaoSubTema to set
     */
    public void setDescricaoSubTema(String descricaoSubTema) {
        this.descricaoSubTema = descricaoSubTema;
    }

    /**
     * @return the dataInsercao
     */
    public Date getDataInsercao() {
        return dataInsercao;
    }

    /**
     * @param dataInsercao the dataInsercao to set
     */
    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    /**
     * @return the dataAtualizacao
     */
    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    /**
     * @param dataAtualizacao the dataAtualizacao to set
     */
    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SubTema other = (SubTema) obj;
        if (this.idSubTema != other.idSubTema && (this.idSubTema == null || !this.idSubTema.equals(other.idSubTema))) {
            return false;
        }
        if ((this.tituloSubTema == null) ? (other.tituloSubTema != null) : !this.tituloSubTema.equals(other.tituloSubTema)) {
            return false;
        }
        if ((this.descricaoSubTema == null) ? (other.descricaoSubTema != null) : !this.descricaoSubTema.equals(other.descricaoSubTema)) {
            return false;
        }
        if (this.dataInsercao != other.dataInsercao && (this.dataInsercao == null || !this.dataInsercao.equals(other.dataInsercao))) {
            return false;
        }
        if (this.dataAtualizacao != other.dataAtualizacao && (this.dataAtualizacao == null || !this.dataAtualizacao.equals(other.dataAtualizacao))) {
            return false;
        }
        if (this.idTema != other.idTema && (this.idTema == null || !this.idTema.equals(other.idTema))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idSubTema != null ? this.idSubTema.hashCode() : 0);
        return hash;
    }

    /**
     * @return the idTema
     */
    public Long getIdTema() {
        return idTema;
    }

    /**
     * @param idTema the idTema to set
     */
    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }


}
