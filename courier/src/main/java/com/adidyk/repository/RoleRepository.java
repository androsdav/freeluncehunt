package com.adidyk.repository;

import com.adidyk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface RoleRepository used for access to data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Repository
public interface  RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * findByNmae - finds role by name.
     * @param name - role name.
     * @return - returns role.
     */
    Role findByName(String name);

}