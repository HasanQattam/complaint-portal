package com.pwc.complaint_portal.repository;

import java.util.Optional;

import com.pwc.complaint_portal.models.ERole;
import com.pwc.complaint_portal.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
