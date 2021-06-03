package com.accounting.dao.impl;

import com.accounting.dao.PaymentDao;
import com.accounting.exception.PaymentDaoException;
import com.accounting.model.Payment;
import com.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    private EntityManagerFactory emf;

    public PaymentDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Payment payment) {
        if (payment == null) {
            throw new PaymentDaoException("Payment is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.persist(payment));
    }

    @Override
    public Payment findById(Long id) {
        return new EntityManagerUtil(emf).performReturningWithinTx(entityManager -> entityManager.find(Payment.class, id));
    }

    @Override
    public Payment findBySum(BigDecimal sum) {
        return new EntityManagerUtil(emf).performReturningWithinTx(em ->
                em.createQuery("select a from Payment a where a.sum = :sum", Payment.class)
                        .setParameter("sum", sum)
                        .getSingleResult()
        );
    }

    @Override
    public void update(Payment payment) {
        if (payment == null) {
            throw new PaymentDaoException("Payment is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.merge(payment));
    }

    @Override
    public void remove(Payment payment) {
        new EntityManagerUtil(emf).performWithinTx(em -> {
            Payment managedPayment = em.merge(payment);
            em.remove(managedPayment);
        });
    }

    @Override
    public List<Payment> findAll() {
        return new EntityManagerUtil(emf).performReturningWithinTx(a -> a.createQuery("select a from Payment a", Payment.class).getResultList());
    }
}
