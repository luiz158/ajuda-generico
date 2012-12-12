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
import br.ajuda.generico.jdbc.annotation.Tabela;
import br.ajuda.generico.jdbc.annotation.Transitorio;
import java.io.Serializable;

/**
 *
 * @author jacoboliveira
 */
@Tabela(nome="ag_subtema",schema="public")
public class SubTema extends Topico implements Serializable {

    @Transitorio
    private static final long serialVersionUID = -8249014547569069393L;
    
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
