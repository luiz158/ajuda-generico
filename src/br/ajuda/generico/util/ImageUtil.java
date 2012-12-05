/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ajuda.generico.util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author jacob_lisboa
 */
public class ImageUtil {

    public ImageUtil() {
    }

    public static Image getImageResource(String name){
        return new javax.swing.ImageIcon(ImageUtil.class.getResource(name)).getImage();
    }

    public static ImageIcon getImageIconResource(String name){
        return new ImageIcon(ImageUtil.class.getResource(name));
    }
}
