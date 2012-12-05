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

import br.ajuda.generico.control.ConstantsControl;
import br.ajuda.generico.control.ControladorDespacho;
import br.ajuda.generico.exceptions.MensagensException;
import java.util.logging.Level;

/**
 *
 * @author jacoboliveira
 */
public class TestControlador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorDespacho c =new ControladorDespacho();
//        c.infoMsg("test info");
//        c.erroMsg("test erro");
//        c.alertaMsg("test alerta");
//        c.sucessoMsg("test sucesso");
        MensagensException m = new MensagensException();
        m.adicMsg(Level.WARNING, "perigo!!!!");
        m.adicMsg(Level.SEVERE, "erro fatality!!!!");

        c.registraEexibe(m);
        //c.exibirMsgs();
        System.exit(0);
    }

}
