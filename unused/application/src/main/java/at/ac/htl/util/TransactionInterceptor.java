package at.ac.htl.util;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Dependent
@Transactional @Interceptor
public class TransactionInterceptor {
    @Inject EntityManager em;
    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {
        var startedTransaction = false;
        if (em.isOpen() && !em.getTransaction().isActive()) {
            em.getTransaction().begin();
            startedTransaction = true;
        }
        Object object;
        try {
            object = ctx.proceed();
            if (startedTransaction) {
                em.getTransaction().commit();
            }
        } catch(Exception e) {
            em.getTransaction().setRollbackOnly();
            throw(e);
        }
        return object;
    }
}