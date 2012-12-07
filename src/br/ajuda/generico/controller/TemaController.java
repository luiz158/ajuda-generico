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
package br.ajuda.generico.controller;

import br.ajuda.generico.dao.ITemaDao;
import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.jdbc.DaoFactory;
import br.ajuda.generico.util.CrudController;
import java.util.List;

/**
 *
 * @author jacoboliveira
 */
public class TemaController implements CrudController<Tema> {

    private DaoFactory daoFactory;

    public TemaController() {
        daoFactory = DaoFactory.getDaoFactory();
    }

    @Override
    public Tema salvar(Tema tema) throws Exception {
        ITemaDao temaDao = daoFactory.getTemaDao();        
        temaDao.savePrepare(tema);
        temaDao.commit();
        return tema;
    }

    @Override
    public Tema alterar(Tema bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tema excluir(Tema bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tema consultaUnicoRetorno(Tema bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Tema> consultaLista(Tema bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
