/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author jacob_lisboa
 */
public class FileHelper extends FileUtils {

    public static String getLocationAppPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 
     * @param p
     */
    public static void criarPastaSenaoExistir(String p) {
        File file = new File(p);
        File[] arquivos = file.listFiles();

        if (arquivos == null) {
            List<String> lista = new ArrayList<String>();
            for (StringTokenizer stringTokenizer = new StringTokenizer(file.getPath(), "\\"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                lista.add(token);
            }
            StringBuilder raiz = new StringBuilder(lista.get(0));
            lista.remove(raiz.toString());
            StringBuilder s = new StringBuilder();
            for (String token : lista) {
                StringBuilder tmp = new StringBuilder();
                tmp.append("\\").append(token);
                s.append(tmp);
                File arquivo = new File(new StringBuilder(raiz).append(s).toString());
                if (!arquivo.exists()) {
                    arquivo.setWritable(true, true);
                    if (arquivo.mkdir()) {
                        System.out.println("pasta criada: " + arquivo);
                    }
                }

            }



        }
    }

    /**
     * obtem um objeto input stream do arquivo
     * @param s
     * @return
     */
    public static InputStream getPath(String s) {
        return FileHelper.class.getResourceAsStream(s);
    }

    /**
     * obtem um objeto input stream convertido para FileInpuStream, do arquivo
     * @param s
     * @return
     */
    public static FileInputStream getPath3(String s) {
        return (FileInputStream) FileHelper.class.getResourceAsStream(s);
    }

    /**
     * obtem o caminho completo do arquivo 
     * @param s
     * @return
     */
    public static String getPath2(String s) {

        return FileHelper.class.getResource(s).getPath();
    }

    /**
     *
     * @param fileInputStream
     * @return
     * @throws IOException
     */
    public static List readLines(InputStream fileInputStream) throws IOException {
        return readLines(fileInputStream, null);
    }

    /**
     *
     * @param fileInputStream
     * @param enconding
     * @return
     * @throws IOException
     */
    private static List readLines(InputStream fileInputStream, String enconding) throws IOException {
        InputStream in = null;
        try {
            in = fileInputStream;
            return IOUtils.readLines(in, enconding);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
