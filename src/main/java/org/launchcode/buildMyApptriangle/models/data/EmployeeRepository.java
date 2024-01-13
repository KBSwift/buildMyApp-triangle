package org.launchcode.buildMyApptriangle.models.data;

import org.launchcode.buildMyApptriangle.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Optional<Employee> findEmployeeByUsername(String employee);
}
