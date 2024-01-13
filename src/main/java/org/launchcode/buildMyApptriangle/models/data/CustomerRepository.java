package org.launchcode.buildMyApptriangle.models.data;

import org.launchcode.buildMyApptriangle.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findCustomerByUsername(String customer);
}
