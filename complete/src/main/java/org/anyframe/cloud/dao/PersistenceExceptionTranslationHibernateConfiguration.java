package org.anyframe.cloud.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Hahn on 2016-01-28.
 */
@Configuration
public class PersistenceExceptionTranslationHibernateConfiguration {

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

//    @Bean
//    public PersistenceExceptionTranslator sqlExceptionTranslator(){
//        return new CustomSQLExceptionTranslator();
//    }
}
