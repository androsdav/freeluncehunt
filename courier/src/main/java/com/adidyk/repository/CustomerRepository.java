package com.adidyk.repository;

import com.adidyk.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface UserRepository used for access to data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
