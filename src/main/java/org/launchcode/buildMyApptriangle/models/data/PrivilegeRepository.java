package org.launchcode.buildMyApptriangle.models.data;

import org.launchcode.buildMyApptriangle.models.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, String> {
    Privilege findByName(String name);
}
