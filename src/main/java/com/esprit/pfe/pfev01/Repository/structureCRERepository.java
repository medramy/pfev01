package com.esprit.pfe.pfev01.Repository;

import com.esprit.pfe.pfev01.Model.structure;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface structureCRERepository extends JpaRepository<structure, Long>{

    //Optional<structure> findByName(String name);
}
