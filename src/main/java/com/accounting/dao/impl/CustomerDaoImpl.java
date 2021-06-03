package com.accounting.dao.impl;

import com.accounting.dao.CustomerDao;
import com.accounting.exception.CustomerDaoException;
import com.accounting.model.Customer;
import com.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private EntityManagerFactory emf;

    public CustomerDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Customer customer) {
        if (customer == null) {
            throw new CustomerDaoException("Customer is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.persist(customer));
    }

    @Override
    public Customer findById(Long id) {
        return new EntityManagerUtil(emf).performReturningWithinTx(entityManager -> entityManager.find(Customer.class, id));
    }

    @Override
    public Customer findByName(String name) {
        return new EntityManagerUtil(emf).performReturningWithinTx(em ->
                em.createQuery("select a from Customer a where a.name = :name", Customer.class)
                        .setParameter("name", name)
                        .getSingleResult()
        );
    }

    @Override
    public List<Customer> findAll() {
        return new EntityManagerUtil(emf).performReturningWithinTx(a -> a.createQuery("select a from Customer a", Customer.class).getResultList());
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerDaoException("Customer is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.merge(customer));
    }

    @Override
    public void remove(Customer customer) {
        new EntityManagerUtil(emf).performWithinTx(em -> {
            Customer managedCustomer = em.merge(customer);
            em.remove(managedCustomer);
        });
    }
}
