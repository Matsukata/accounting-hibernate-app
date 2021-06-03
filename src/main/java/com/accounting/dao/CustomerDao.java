package com.accounting.dao;

import com.accounting.model.Customer;

import java.util.List;

public interface CustomerDao {
    void save(Customer customer);

    Customer findById(Long id);

    Customer findByName(String name);

    List<Customer> findAll();

    void update(Customer customer);

    void remove(Customer customer);
}
