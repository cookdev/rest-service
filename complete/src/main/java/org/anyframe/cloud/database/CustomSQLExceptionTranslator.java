//package org.anyframe.cloud.database;
//
//import org.anyframe.cloud.database.exception.DataIntegrityViolationACException;
//import org.springframework.dao.*;
//import org.springframework.dao.support.PersistenceExceptionTranslator;
//
///**
// * Created by Hahn on 2016-01-27.
// */
//public class CustomSQLExceptionTranslator implements PersistenceExceptionTranslator {
//
//    @Override
//    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
////,((DataIntegrityViolationException) ex).getConstraintName()
//        return ex instanceof DataIntegrityViolationException ?
//                new DataIntegrityViolationACException(ex.getMessage(),ex, ((DataIntegrityViolationException) ex))
//                : (DataAccessException) ex;
////        return ex instanceof HibernateException?this.convertHibernateAccessException((HibernateException)ex):null;
//    }
//
////    for SQLExceptionTranslator
////    @Override
////    public DataAccessException translate(String task, String sql, SQLException e) {
////        HibernateException he = new HibernateException(task, e);
////        return convertHibernateAccessException(he);
////    }
//
////    protected DataAccessException convertHibernateAccessException(HibernateException ex) {
////
////        if(ex instanceof JDBCConnectionException) {
////            return new DataAccessResourceFailureException(ex.getMessage(), ex);
////        } else if(ex instanceof SQLGrammarException) {
////            SQLGrammarException jdbcEx5 = (SQLGrammarException)ex;
////            return new InvalidDataAccessResourceUsageException(ex.getMessage() + "; SQL [" + jdbcEx5.getSQL() + "]", ex);
////        } else if(ex instanceof QueryTimeoutException) {
////            QueryTimeoutException jdbcEx4 = (QueryTimeoutException)ex;
////            return new org.springframework.dao.QueryTimeoutException(ex.getMessage() + "; SQL [" + jdbcEx4.getSQL() + "]", ex);
////        } else if(ex instanceof LockAcquisitionException) {
////            LockAcquisitionException jdbcEx3 = (LockAcquisitionException)ex;
////            return new CannotAcquireLockException(ex.getMessage() + "; SQL [" + jdbcEx3.getSQL() + "]", ex);
////        } else if(ex instanceof PessimisticLockException) {
////            PessimisticLockException jdbcEx2 = (PessimisticLockException)ex;
////            return new PessimisticLockingFailureException(ex.getMessage() + "; SQL [" + jdbcEx2.getSQL() + "]", ex);
////        } else if(ex instanceof ConstraintViolationException) {
////            ConstraintViolationException jdbcEx1 = (ConstraintViolationException)ex;
////            return new DataIntegrityViolationACException(ex.getMessage() + "; SQL [" + jdbcEx1.getSQL() + "]; constraint [" + jdbcEx1.getConstraintName() + "]", ex, jdbcEx1.getConstraintName());
////        } else if(ex instanceof DataException) {
////            DataException jdbcEx = (DataException)ex;
////            return new DataIntegrityViolationACException(ex.getMessage() + "; SQL [" + jdbcEx.getSQL() + "]", ex);
////        } else {
////            return (DataAccessException)(ex instanceof JDBCException ?new HibernateJdbcException((JDBCException)ex):(ex instanceof QueryException ?new HibernateQueryException((QueryException)ex):(ex instanceof NonUniqueResultException?new IncorrectResultSizeDataAccessException(ex.getMessage(), 1, ex):(ex instanceof NonUniqueObjectException?new DuplicateKeyException(ex.getMessage(), ex):(ex instanceof PropertyValueException?new DataIntegrityViolationACException(ex.getMessage(), ex):(ex instanceof PersistentObjectException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof TransientObjectException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof ObjectDeletedException?new InvalidDataAccessApiUsageException(ex.getMessage(), ex):(ex instanceof UnresolvableObjectException?new HibernateObjectRetrievalFailureException((UnresolvableObjectException)ex):(ex instanceof WrongClassException?new HibernateObjectRetrievalFailureException((WrongClassException)ex):(ex instanceof StaleObjectStateException?new HibernateOptimisticLockingFailureException((StaleObjectStateException)ex):(ex instanceof StaleStateException?new HibernateOptimisticLockingFailureException((StaleStateException)ex):(ex instanceof OptimisticEntityLockException ?new HibernateOptimisticLockingFailureException((OptimisticEntityLockException)ex):(ex instanceof PessimisticEntityLockException ?(ex.getCause() instanceof LockAcquisitionException?new CannotAcquireLockException(ex.getMessage(), ex.getCause()):new PessimisticLockingFailureException(ex.getMessage(), ex)):new HibernateSystemException(ex)))))))))))))));
////        }
////
////    }
//}
