package com.emploi.repository;

import com.emploi.model.CompanyLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyLogoRepo extends JpaRepository<CompanyLogo, Long> {
}
