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
import br.ajuda.generico.util.AbstractController;
import br.ajuda.generico.util.CrudController;
import br.ajuda.generico.util.SqlUtil;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author jacoboliveira
 */
public class TemaController extends AbstractController implements CrudController<Tema> {

    private ITemaDao temaDao;

    public TemaController() throws Exception {
        super();
        temaDao = daoFactory.getTemaDao();
    }

    @Override
    public Tema salvar(Tema tema) throws Exception {
        temaDao.savePrepare(tema);
        temaDao.commit();
        return tema;
    }

    @Override
    public Tema alterar(Tema tema) throws Exception {
        temaDao.updatePrepare(tema);
        temaDao.commit();
        return tema;
    }

    @Override
    public Tema excluir(Tema tema) throws Exception {
        PreparedStatement p = temaDao.prepare("DELETE FROM "
                + managerAnnotationEntities.getNomeTabela(tema)
                + " WHERE " + "id_tema=?");
        tema = temaDao.prepareQueryPorIdsReturnSingleBean(tema);
        p.setLong(1, tema.getId());
        p.execute();
        temaDao.commit();
        return tema;
    }

    @Override
    public Tema consultaUnicoRetorno(Tema bean) throws Exception {
        Tema tema = temaDao.prepareQueryReturnSingleBean(bean);
        temaDao.commit();
        return tema;
    }

    @Override
    public List<Tema> consultaLista(Tema bean) throws Exception {
        return SqlUtil.parseListMapToListBean(temaDao.cexecuteQuery("SELECT * FROM " + managerAnnotationEntities.getNomeTabela(bean)), Tema.class);
    }
}
