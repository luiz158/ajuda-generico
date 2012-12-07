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
package br.ajuda.generico.util;

import java.util.List;

/**
 *
 * @author jacoboliveira
 */
public interface CrudController<B> {

    public B salvar(B bean) throws Exception;

    public B alterar(B bean) throws Exception;

    public B excluir(B bean) throws Exception;

    public B consultaUnicoRetorno(B bean) throws Exception;

    public List<B> consultaLista(B bean) throws Exception;
}
