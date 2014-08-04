package de.mare.mobile.utils;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

/**
 * 
 * @author mreinhardt
 * 
 */
public abstract class EmHelper {

  @SuppressWarnings("rawtypes")
  public static void setEntityManager(final Object emAware, final EntityManager em) throws Exception {
    Field field = null;
    if (emAware != null) {
      boolean continueLoop = emAware.getClass() != null;
      Class parent = emAware.getClass();
      while (continueLoop) {
        try {
          field = parent.getDeclaredField("entityManager");
          // found value, return
          continueLoop = false;
        } catch (final Exception ex) {
          // ignore and continue
          parent = parent.getSuperclass();
          continueLoop = parent != null;
        }
      }
      field.setAccessible(true);
      field.set(emAware, em);
      field.setAccessible(false);
    }

  }

  public static void execute(final Runnable r, final EntityManager em) throws Exception {
    EntityTransaction trx = null;
    try {
      trx = em.getTransaction();
      trx.begin();
      r.execute(em);
      trx.commit();
    } catch (final RollbackException e) {} catch (final RuntimeException e) {
      if (trx != null && trx.isActive()) {
        trx.rollback();
      }
      throw e;
    } finally {
      if (trx.isActive()) {
        trx.commit();
      }
    }
  }

  public interface Runnable {

    void execute(EntityManager em) throws Exception;
  }
}
