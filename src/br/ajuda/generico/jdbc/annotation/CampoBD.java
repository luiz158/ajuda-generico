/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ajuda.generico.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author jacob
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD,ElementType.METHOD})
public @interface CampoBD {
    public String nome() default "";
    public boolean obrigatorio() default false;
    public String mensagem() default "";
    public String nomeImpCampo() default "";
}
