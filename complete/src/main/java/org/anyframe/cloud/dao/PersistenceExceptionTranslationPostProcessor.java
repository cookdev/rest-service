package org.anyframe.cloud.dao;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.dao.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * Created by Hahn on 2016-01-29.
 */
public class PersistenceExceptionTranslationPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {
    private Class<? extends Annotation> repositoryAnnotationType = Repository.class;

    public PersistenceExceptionTranslationPostProcessor() {
    }

    public void setRepositoryAnnotationType(Class<? extends Annotation> repositoryAnnotationType) {
        Assert.notNull(repositoryAnnotationType, "\'repositoryAnnotationType\' must not be null");
        this.repositoryAnnotationType = repositoryAnnotationType;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        if(!(beanFactory instanceof ListableBeanFactory)) {
            throw new IllegalArgumentException("Cannot use PersistenceExceptionTranslator autodetection without ListableBeanFactory");
        } else {
            this.advisor = new PersistenceExceptionTranslationAdvisor((ListableBeanFactory)beanFactory, this.repositoryAnnotationType);
        }
    }
}