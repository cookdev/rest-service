package org.anyframe.cloud.database;


import org.anyframe.cloud.database.exception.DataIntegrityViolationACException;
import org.hibernate.*;
import org.hibernate.QueryTimeoutException;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.hibernate.exception.*;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate5.HibernateQueryException;
import org.springframework.orm.hibernate5.HibernateSystemException;

import javax.persistence.PersistenceException;

/**
 * Created by Hahn on 2016-01-28.
 */
public class AnyframeHibernateJpaDialect extends HibernateJpaDialect {

    public AnyframeHibernateJpaDialect(){
        super();
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return ex instanceof HibernateException ?this.convertHibernateAccessException((HibernateException)ex):(ex instanceof PersistenceException && ex.getCause() instanceof HibernateException?this.convertHibernateAccessException((HibernateException)ex.getCause()): EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(ex));
    }

    @Override
    protected DataAccessException convertHibernateAccessException(HibernateException ex) {

        if(ex instanceof JDBCConnectionException) {
            return new DataAccessResourceFailureException(ex.getMessage(), ex);
        } else if(ex instanceof SQLGrammarException) {
            SQLGrammarException jdbcEx5 = (SQLGrammarException)ex;
            return new InvalidDataAccessResourceUsageException(ex.getMessage() + "; SQL [" + jdbcEx5.getSQL() + "]", ex);
        } else if(ex instanceof QueryTimeoutException) {
            QueryTimeoutException jdbcEx4 = (QueryTimeoutException)ex;
            return new org.springframework.dao.QueryTimeoutException(ex.getMessage() + "; SQL [" + jdbcEx4.getSQL() + "]", ex);
        } else if(ex instanceof LockAcquisitionException) {
            LockAcquisitionException jdbcEx3 = (LockAcquisitionException)ex;
            return new CannotAcquireLockException(ex.getMessage() + "; SQL [" + jdbcEx3.getSQL() + "]", ex);
        } else if(ex instanceof PessimisticLockException) {
            PessimisticLockException jdbcEx2 = (PessimisticLockException)ex;
            return new PessimisticLockingFailureException(ex.getMessage() + "; SQL [" + jdbcEx2.getSQL() + "]", ex);
        } else if(ex instanceof ConstraintViolationException) {
            ConstraintViolationException jdbcEx1 = (ConstraintViolationException)ex;
            return new DataIntegrityViolationACException(ex.getMessage() + "; SQL [" + jdbcEx1.getSQL() + "]; constraint [" + jdbcEx1.getConstraintName() + "]", ex, jdbcEx1.getConstraintName());
        } else if(ex instanceof DataException) {
            DataException jdbcEx = (DataException)ex;
            return new DataIntegrityViolationACException(ex.getMessage() + "; SQL [" + jdbcEx.getSQL() + "]", ex);
        } else {
            return (DataAccessException)(ex instanceof JDBCException ?new HibernateJdbcException((JDBCException)ex):(ex instanceof QueryException ?new HibernateQueryException((QueryException)ex):(ex instanceof NonUniqueResultException?new IncorrectResultSizeDataAccessException(ex.getMessage(), 1, ex):(ex instanceof NonUniqueObjectException?new DuplicateKeyException(ex.getMessage(), ex):(ex instanceof PropertyValueException?new DataIntegrityViolationACException(ex.getMessage(), ex):(ex instanceof PersistentObjectException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof TransientObjectException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof ObjectDeletedException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof UnresolvableObjectException?new HibernateObjectRetrievalFailureException((UnresolvableObjectException)ex):(ex instanceof WrongClassException?new HibernateObjectRetrievalFailureException((WrongClassException)ex):(ex instanceof StaleObjectStateException?new HibernateOptimisticLockingFailureException((StaleObjectStateException)ex):(ex instanceof StaleStateException?new HibernateOptimisticLockingFailureException((StaleStateException)ex):(ex instanceof OptimisticEntityLockException ?new HibernateOptimisticLockingFailureException((OptimisticEntityLockException)ex):(ex instanceof PessimisticEntityLockException ?(ex.getCause() instanceof LockAcquisitionException?new CannotAcquireLockException(ex.getMessage(), ex.getCause()):new PessimisticLockingFailureException(ex.getMessage(), ex)):new HibernateSystemException(ex)))))))))))))));
        }

    }
}
