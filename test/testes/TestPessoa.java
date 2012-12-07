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
package testes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jacoboliveira
 */
public class TestPessoa implements Serializable{
    private static final long serialVersionUID = -5136997122865439981L;

    private String nome;
    private Date dataAniver;
    private Double salario;
    private Long nArquivos;
    private Integer idade;
    private Short b;
    private Boolean sexo;
    private BigDecimal total;

    public TestPessoa() {
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestPessoa other = (TestPessoa) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    /**
     * @return the dataAniver
     */
    public Date getDataAniver() {
        return dataAniver;
    }

    /**
     * @param dataAniver the dataAniver to set
     */
    public void setDataAniver(Date dataAniver) {
        this.dataAniver = dataAniver;
    }

    /**
     * @return the salario
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    /**
     * @return the nArquivos
     */
    public Long getnArquivos() {
        return nArquivos;
    }

    /**
     * @param nArquivos the nArquivos to set
     */
    public void setnArquivos(Long nArquivos) {
        this.nArquivos = nArquivos;
    }

    /**
     * @return the idade
     */
    public Integer getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    /**
     * @return the b
     */
    public Short getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Short b) {
        this.b = b;
    }

    /**
     * @return the sexo
     */
    public Boolean getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
