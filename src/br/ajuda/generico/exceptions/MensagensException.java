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

package br.ajuda.generico.exceptions;

import br.ajuda.generico.control.MensagemLogSistema;
import br.ajuda.generico.control.MensagemSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author jacoboliveira
 */
public class MensagensException extends Exception {
    
    private List<MensagemLogSistema> mensagens = new ArrayList();
    /**
     * Creates a new instance of <code>MensagensException</code> without detail message.
     */
    public MensagensException() {
    }


    /**
     * Constructs an instance of <code>MensagensException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MensagensException(Level tipo,String msg) {
        super(msg);
        mensagens.add(new MensagemLogSistema(tipo, msg));
    }

    public void adicMsg(Level tipo,String msg) {
        mensagens.add(new MensagemLogSistema(tipo, msg));
    }

    public List<MensagemLogSistema> getMensagens() {
        return mensagens;
    }
}
