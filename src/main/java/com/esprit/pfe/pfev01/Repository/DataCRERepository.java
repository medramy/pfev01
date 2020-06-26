package com.esprit.pfe.pfev01.Repository;

import com.esprit.pfe.pfev01.Model.DataCRE;
import com.esprit.pfe.pfev01.Model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DataCRERepository extends JpaRepository<DataCRE, Long> {
    //Optional<DataCRE> findByRecords(Records records);
}
