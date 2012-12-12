/*
 *  Copyright (C) 2012 jacoboliveira
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.ajuda.generico.entities;

import br.ajuda.generico.jdbc.annotation.CampoBD;
import br.ajuda.generico.jdbc.annotation.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jacoboliveira
 */
public class Topico implements Serializable{
    @Id
    @CampoBD(nome="id")
    protected Long id;
    @CampoBD(nome="titulo",obrigatorio=true,mensagem="Preencha o campo Titulo!")
    protected String titulo;
    @CampoBD(nome="descricao")
    protected String descricao;
    @CampoBD(nome="data_insercao")
    protected Date dataInsercao;
    @CampoBD(nome="data_atualizacao")
    protected Date dataAtualizacao;

    public Topico() {
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Topico other = (Topico) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.titulo == null) ? (other.titulo != null) : !this.titulo.equals(other.titulo)) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if (this.dataInsercao != other.dataInsercao && (this.dataInsercao == null || !this.dataInsercao.equals(other.dataInsercao))) {
            return false;
        }
        if (this.dataAtualizacao != other.dataAtualizacao && (this.dataAtualizacao == null || !this.dataAtualizacao.equals(other.dataAtualizacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
