package com.accounting.dao.impl;

import com.accounting.dao.ContractDao;
import com.accounting.exception.ContractDaoException;
import com.accounting.model.Contract;
import com.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ContractDaoImpl implements ContractDao {
    private EntityManagerFactory emf;

    public ContractDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Contract contract) {
        if (contract == null) {
            throw new ContractDaoException("Contract is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.persist(contract));
    }

    @Override
    public Contract findById(Long id) {
        return new EntityManagerUtil(emf).performReturningWithinTx(entityManager -> entityManager.find(Contract.class, id));
    }

    @Override
    public Contract findByName(String name) {
        return new EntityManagerUtil(emf).performReturningWithinTx(em ->
                em.createQuery("select a from Contract a where a.name = :name", Contract.class)
                        .setParameter("name", name)
                        .getSingleResult()
        );
    }

    @Override
    public List<Contract> findAll() {
        return new EntityManagerUtil(emf).performReturningWithinTx(a -> a.createQuery("select a from Contract a", Contract.class).getResultList());
    }

    @Override
    public void update(Contract contract) {
        if (contract == null) {
            throw new ContractDaoException("Contract is not provided", new Throwable().getCause());
        }
        new EntityManagerUtil(emf).performWithinTx(entityManager -> entityManager.merge(contract));
    }

    @Override
    public void remove(Contract contract) {
        new EntityManagerUtil(emf).performWithinTx(em -> {
            Contract managedContract = em.merge(contract);
            em.remove(managedContract);
        });
    }
}
