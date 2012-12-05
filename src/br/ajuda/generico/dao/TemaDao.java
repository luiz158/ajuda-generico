/*
 *  Copyright (C) 2012 jacob
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
package br.ajuda.generico.dao;

import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.jdbc.HsqldbDaoFactory;

/**
 * 
 * @author jacob
 */
public class TemaDao extends HsqldbDaoFactory<Tema> implements ITemaDao {

    private static ITemaDao temaDao;

    public TemaDao() throws Exception {
        super();
    }

    public static ITemaDao getTemaDao() throws Exception {
        if (temaDao == null) {
            temaDao = new TemaDao();
        }
        return temaDao;
    }
}
