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

import br.ajuda.generico.dao.ISubTemaDao;
import br.ajuda.generico.entities.SubTema;
import br.ajuda.generico.util.AbstractController;
import br.ajuda.generico.util.CrudController;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author jacoboliveira
 */
public class SubTemaController extends AbstractController implements CrudController<SubTema> {

    private ISubTemaDao subTemaDao;

    public SubTemaController() throws Exception {
        super();
        subTemaDao = daoFactory.getSubTemaDao();
    }

    @Override
    public SubTema salvar(SubTema subTema) throws Exception {
        subTemaDao.savePrepare(subTema);
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public SubTema alterar(SubTema subTema) throws Exception {
        subTemaDao.updatePrepare(subTema);
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public SubTema excluir(SubTema subTema) throws Exception {
        PreparedStatement psmt = subTemaDao.prepare("DELETE FROM " + managerAnnotationEntities.getNomeTabela(subTema) + " WHERE id_subtema=?");
        psmt.setLong(1, subTema.getId());
        psmt.execute();
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public SubTema consultaUnicoRetorno(SubTema subTema) throws Exception {
        SubTema s = subTemaDao.prepareQueryReturnSingleBean(subTema);
        subTemaDao.commit();
        return s;
    }

    @Override
    public List<SubTema> consultaLista(SubTema subTema) throws Exception {
        List l = subTemaDao.cexecuteQuery("SELECT FROM " + managerAnnotationEntities.getNomeTabela(subTema));
        subTemaDao.commit();
        return l;
    }
}
