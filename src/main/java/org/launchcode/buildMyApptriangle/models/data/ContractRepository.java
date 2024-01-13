package org.launchcode.buildMyApptriangle.models.data;

import org.launchcode.buildMyApptriangle.models.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, String> {
}
