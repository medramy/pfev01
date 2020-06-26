package com.esprit.pfe.pfev01.Repository;

import com.esprit.pfe.pfev01.Model.ERole;
import com.esprit.pfe.pfev01.Model.Role;
import com.esprit.pfe.pfev01.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//    List<User> findByRoles(Set<Role> roles);


}
