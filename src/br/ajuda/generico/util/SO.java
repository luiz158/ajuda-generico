/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ajuda.generico.util;

import java.io.File;

/**
 *
 * @author jacoblisboa
 */
public class SO {
 /**
  * 
  * retorna a barra "/" se for UNIX ou Linux, se nao retorna "\\" se for Microsoft Windows.
  * 
  * @return "/" ou "\\", dependendo do sistema
  */
 public static String getSepArqSO(){
     return File.separator;
 }

}
