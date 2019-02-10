package com.revolut.moneytransfer.dao.jta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.persistence.EntityManager;
/**
 * A qualifier {@code Qualifier} to be used to qualify {@code EntityManager} with  Resource Local transactions 
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see Qualifier
 * @see EntityManager
 * @since 1.0
 */
 
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD,ElementType.PARAMETER})
public @interface ResourceLocal {

}
