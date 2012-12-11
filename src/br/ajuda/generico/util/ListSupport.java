/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import java.awt.Color;
import javax.swing.ListModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author jacoboliveira
 */
public class ListSupport {

    private JXList list;

    public ListSupport(JXList list) {
        this.list = list;
    }

    public void configureJXList() {
        list.setHighlighters(HighlighterFactory.createSimpleStriping(HighlighterFactory.CLASSIC_LINE_PRINTER));
        list.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW,
                Color.DARK_GRAY, Color.WHITE));
        list.setRolloverEnabled(true);
    }

    public void filtrarBusca(final String texto,final String nomeCampo) {
        list.setRowFilter(new FilterList(texto,nomeCampo));
    }

    private class FilterList extends RowFilter<ListModel, Integer> {

        String texto,nomeCampo;

        public FilterList() {
        }

        public FilterList(String texto,String nomeCampo) {
            this.texto = texto;
            this.nomeCampo = nomeCampo;
        }

        @Override
        public boolean include(Entry<? extends ListModel, ? extends Integer> entry) {
            Object bean =entry.getValue(entry.getIdentifier().intValue());
            Object valor = BeanHelper.getPropriedade(bean, nomeCampo);
            boolean result = String.valueOf(valor).contains(texto);
            return result;
        }
    }
}
