package com.accounting.dao;

import com.accounting.model.Contract;

import java.util.List;

public interface ContractDao {
    void save(Contract contract);

    Contract findById(Long id);

    Contract findByName(String name);

    List<Contract> findAll();

    void update(Contract contract);

    void remove(Contract contract);
}
