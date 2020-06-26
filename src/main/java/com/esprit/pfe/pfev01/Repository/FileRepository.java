package com.esprit.pfe.pfev01.Repository;

import com.esprit.pfe.pfev01.Model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByName(String name);
}
