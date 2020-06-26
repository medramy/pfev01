package com.esprit.pfe.pfev01.Repository;

import com.esprit.pfe.pfev01.Model.ERole;
import com.esprit.pfe.pfev01.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
