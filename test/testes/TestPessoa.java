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
import java.util.Date;

/**
 *
 * @author jacoboliveira
 */
public class TestPessoa implements Serializable{
    private static final long serialVersionUID = -5136997122865439981L;

    private String nome;
    private Date dataAniver;

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

}
