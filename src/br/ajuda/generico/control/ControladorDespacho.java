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
package br.ajuda.generico.control;

import br.ajuda.generico.exceptions.MensagensException;
import br.ajuda.generico.util.JMessageUtil;
import br.ajuda.generico.util.LogSis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author jacoboliveira
 */
public class ControladorDespacho {

    private List<MensagemSistema> mensagens;
    private Map<String, Object> paramSis;
    private LogSis logSis;

    public ControladorDespacho() {
        mensagens = new ArrayList();
        paramSis = new HashMap();
        logSis = new LogSis();
    }

    /**
     * mostra mensagem com formato de informacao para o usuario
     * @param msg
     */
    public void infoMsg(String msg) {
        mensagens.add(new MensagemSistema(ConstantsControl.MSG_INFO, msg));
    }

    /**
     * mostra mensagem com formato de aviso para o usuario
     * @param msg
     */
    public void alertaMsg(String msg) {
        mensagens.add(new MensagemSistema(ConstantsControl.MSG_ALERT, msg));
    }

    /**
     * mostra mensagem com formato de erro para o usuario
     * @param msg
     */
    public void erroMsg(String msg) {
        mensagens.add(new MensagemSistema(ConstantsControl.MSG_ERRO, msg));
    }

    /**
     * mostra mensagem com formato de erro para o usuario
     * @param msg
     */
    public void sucessoMsg(String msg) {
        mensagens.add(new MensagemSistema(ConstantsControl.MSG_SUCESSO, msg));
    }

    public void exibirMsgs() {
        StringBuilder s = new StringBuilder();
        for (MensagemSistema msgSis : mensagens) {
            switch (msgSis.getTipo()) {
                case ConstantsControl.MSG_ALERT:
                    s.append("[Alerta]: \n").append(msgSis.getMsg()).append("\n");
                    break;
                case ConstantsControl.MSG_INFO:
                    s.append("[Informação]: \n").append(msgSis.getMsg()).append("\n");
                    break;
                case ConstantsControl.MSG_ERRO:
                    s.append("[Erro]: \n").append(msgSis.getMsg()).append("\n");
                    break;
                case ConstantsControl.MSG_SUCESSO:
                    s.append("[Sucesso]: \n").append(msgSis.getMsg()).append("\n");
                    break;
            }

        }
        JMessageUtil.showMensagensSistema(new javax.swing.JFrame(), s.toString());
        mensagens.clear();
    }

    /**
     * adiciona/altera o parametro passado
     * @param chave identificador unicos do parametro
     * @param valor valor do objeto a ser passado
     */
    public void setParam(String chave, Object valor) {
        //se contem a chave, entao eh removido e adicionado novamente
        if (paramSis.containsKey(chave)) {
            paramSis.remove(chave);
            paramSis.put(chave, valor);
        } else {
            //senao o valor eh adicionado
            paramSis.put(chave, valor);
        }
    }

    public Object getParam(String key){
        return paramSis.get(key);
    }

    /**
     * remove o parametro
     * @param chave identificador unicos do parametro
     */
    public void removerParam(String chave) {
        //se ele estiver no HashMap, entao remove.
        if (paramSis.containsKey(chave)) {
            paramSis.remove(chave);
        }
    }

    public void despachar(Visao visao) {
        visao.setControladorDespacho(this);
    }

    /**
     * registra no log sistema e exibi na caixa de dialogo do sistema
     * @param ex excecao gerada pelo sistema
     */
    public void registraEexibe(Throwable ex) {

        if (ex instanceof MensagensException) {
            MensagensException msgEx = (MensagensException) ex;
            List<MensagemLogSistema> msgs = msgEx.getMensagens();
            StringBuilder s = new StringBuilder();
            for (MensagemLogSistema msgSis : msgs) {                
                logSis.log(msgSis.getLevel(),msgSis.getMsg(),msgEx);                
                s.append("-> Mensagem: ").append(msgSis.getMsg()).append("\n").append(logSis.getPrintStackTrace(msgEx.getClass().getName())).append("\n");
            }
            
            JMessageUtil.showMensagensSistema(new javax.swing.JFrame(), s.toString());
        }
    }
}
