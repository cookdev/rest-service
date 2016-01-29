package org.anyframe.cloud.dao;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.dao.support.PersistenceExceptionTranslationInterceptor;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import java.lang.annotation.Annotation;

/**
 * Created by Hahn on 2016-01-29.
 */
public class PersistenceExceptionTranslationAdvisor extends AbstractPointcutAdvisor {
    private final PersistenceExceptionTranslationInterceptor advice;
    private final AnnotationMatchingPointcut pointcut;

    public PersistenceExceptionTranslationAdvisor(PersistenceExceptionTranslator persistenceExceptionTranslator, Class<? extends Annotation> repositoryAnnotationType) {
        this.advice = new PersistenceExceptionTranslationInterceptor(persistenceExceptionTranslator);
        this.pointcut = new AnnotationMatchingPointcut(repositoryAnnotationType, true);
    }

    PersistenceExceptionTranslationAdvisor(ListableBeanFactory beanFactory, Class<? extends Annotation> repositoryAnnotationType) {
        this.advice = new PersistenceExceptionTranslationInterceptor(beanFactory);
        this.pointcut = new AnnotationMatchingPointcut(repositoryAnnotationType, true);
    }

    public Advice getAdvice() {
        return this.advice;
    }

    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
