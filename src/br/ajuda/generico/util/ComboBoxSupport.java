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

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author jacoboliveira
 */
public class ComboBoxSupport<T> {

    private DefaultComboBoxModel defaultComboBoxModel;
    private JComboBox combo;

    public ComboBoxSupport(JComboBox combo) {
        this.combo = combo;
        defaultComboBoxModel = new DefaultComboBoxModel();
        combo.setModel(defaultComboBoxModel);
    }

    
    public void activeAutoComplete(String definirNomeCampoAutoComplete){
        new AutoCompleteJComboBoxer(combo,definirNomeCampoAutoComplete);
    }

    public DefaultComboBoxModel getModel() {
        return defaultComboBoxModel;
    }

    public void addItensCombo(List<T> listaItens) {
        for (T item : listaItens) {
            defaultComboBoxModel.addElement(item);
        }
    }

    public void selecionarItem(T item) {
        defaultComboBoxModel.setSelectedItem(item);
    }

    public boolean isIndexSelecionadoZero() {
        return (combo.getSelectedIndex() == 0);
    }

    public T getItemSelecionado() {
        return (T) defaultComboBoxModel.getSelectedItem();
    }

    public List<T> getItensSelecionado() {
        List<T> list = new ArrayList<T>();

        Object[] selectedObjects = combo.getSelectedObjects();
        for (Object selected : selectedObjects) {
            list.add((T) selected);
        }
        return list;
    }

    public T[] getArrayItensSelecionado() {
        List<T> list = new ArrayList<T>();

        Object[] selectedObjects = combo.getSelectedObjects();
        for (Object selected : selectedObjects) {
            list.add((T) selected);
        }
        return (T[]) list.toArray();
    }

    public void addItensCombo(T... itens) {
        for (T item : itens) {
            defaultComboBoxModel.addElement(item);
        }
    }
}
