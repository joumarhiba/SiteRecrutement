package com.emploi.repository;

import com.emploi.model.Company;
import com.emploi.model.Offre;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OffreRepo extends JpaRepository<Offre, Long> {


    @Query("From Offre o where o.status = true")
    List<Offre> getValidatedOffres();
    Offre findByProfil(String profil);

    //@Query("FROM Offre o WHERE o.company = ?1")
    List<Offre> findOffreByCompany(Company company);
}
