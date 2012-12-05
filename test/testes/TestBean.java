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

import br.ajuda.generico.jdbc.annotation.CampoBD;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author jacoboliveira
 */
public class TestBean {
    @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora A",nomeImpCampo="A")
    String a;
    @CampoBD(nome="bb",obrigatorio=true,mensagem="preencha agora B",nomeImpCampo="B")
    int b=-1;
 //   @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    float c;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    double d;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    Date f;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    BigInteger g;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    Double h;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    Float i;
   // @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    Integer j;
    @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora L",nomeImpCampo="L")
    char l;
 //   @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    Character m;
  //  @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora",nomeImpCampo="A")
    short n;
    @CampoBD(nome="la_la",obrigatorio=true,mensagem="preencha agora O",nomeImpCampo="O")
    byte o=-1;

}
