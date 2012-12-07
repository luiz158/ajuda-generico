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

package br.ajuda.generico.controladordespacho;

import java.io.Serializable;
import java.util.logging.Level;

/**
 *
 * @author jacoboliveira
 */
public class MensagemLogSistema implements Serializable{
    private static final long serialVersionUID = 4325788200378493993L;
    private Level level;
    private String msg;

    public MensagemLogSistema() {
    }

    public MensagemLogSistema(Level level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
