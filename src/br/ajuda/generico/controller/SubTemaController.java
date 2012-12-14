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
import br.ajuda.generico.dao.ITemaDao;
import br.ajuda.generico.entities.SubTema;
import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.util.AbstractController;
import br.ajuda.generico.util.CrudController;
import br.ajuda.generico.util.SqlUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author jacoboliveira
 */
public class SubTemaController extends AbstractController implements CrudController<SubTema> {

    private ISubTemaDao subTemaDao;
    private ITemaDao temaDao;

    public SubTemaController() throws Exception {
        super();
        subTemaDao = daoFactory.getSubTemaDao();
        temaDao = daoFactory.getTemaDao();
    }

    @Override
    public SubTema salvar(SubTema subTema) throws Exception {
        subTema.setIdTema(subTema.getTema().getId());
        subTemaDao.savePrepare(subTema);
        PreparedStatement p = subTemaDao.getConnection().prepareStatement(
                "SELECT * FROM "+managerAnnotationEntities.getNomeTabela(subTema),
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = p.executeQuery();
        rs.last();
        subTema = SqlUtil.converterResultSetParaBean(p, rs, SubTema.class);        
        subTemaDao.commit();
        if(rs!=null){
            rs.close();
        }
        return subTema;
    }

    @Override
    public SubTema alterar(SubTema subTema) throws Exception {
        subTema.setIdTema(subTema.getTema().getId());
        subTemaDao.updatePrepare(subTema);
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public SubTema excluir(SubTema subTema) throws Exception {
        subTema.setIdTema(subTema.getTema().getId());
        PreparedStatement psmt = subTemaDao.prepare("DELETE FROM " + managerAnnotationEntities.getNomeTabela(subTema) + " WHERE id_subtema=?");
        psmt.setLong(1, subTema.getId());
        psmt.execute();
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public SubTema consultaUnicoRetorno(SubTema subTema) throws Exception {

        subTema = subTemaDao.prepareQueryReturnSingleBean(subTema);
        subTema.setTema(temaDao.prepareQueryPorIdsReturnSingleBean(new Tema(subTema.getIdTema())));
        subTemaDao.commit();
        return subTema;
    }

    @Override
    public List<SubTema> consultaLista(SubTema subTema) throws Exception {
        List l = null;
        String sql = "SELECT * FROM " + managerAnnotationEntities.getNomeTabela(subTema);
        if (subTema.getIdTema() != null) {
            l = subTemaDao.cexecuteQuery(sql + " WHERE id_tema=" + subTema.getIdTema());
        } else {
            l = subTemaDao.cexecuteQuery(sql);
        }
        subTemaDao.commit();
        return SqlUtil.parseListMapToListBean(l, SubTema.class);
    }
}
