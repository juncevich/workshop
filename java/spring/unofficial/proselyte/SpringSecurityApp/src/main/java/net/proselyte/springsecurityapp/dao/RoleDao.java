package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.proselyte.springsecurityapp.model.Role;

/**
 * Role dao
 * 
 * Created by alex on 29.01.17.
 */
public interface RoleDao extends JpaRepository<Role, Long> {

}
