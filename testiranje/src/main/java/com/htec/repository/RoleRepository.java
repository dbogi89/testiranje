package com.htec.repository;


import com.htec.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dbogicevic
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
