package org.launchcode.buildMyApptriangle.models.data;

import org.launchcode.buildMyApptriangle.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByName(String name);
}
