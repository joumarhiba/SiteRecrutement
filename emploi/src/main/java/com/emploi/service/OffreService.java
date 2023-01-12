package com.emploi.service;

import com.emploi.exception.UserNotFoundException;
import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.model.UserRole;
import com.emploi.repository.OffreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreService {
    private final OffreRepo offreRepo;

    public Offre addOffre(Offre offre){
       offre.setAdmin(new Admin(Long.valueOf(1), "admin", "admin@gmail.com", "12345678", UserRole.ADMIN, null));
        offre.setCompany(new Company(Long.valueOf(3),"company", "company@gmail.com", "12345678", "AAAAAAAAAA", "0612347890",UserRole.COMPANY, null));
        return offreRepo.save(offre);
    }

    public Offre findOffreById(Long id) {
        return offreRepo.findById(id).orElseThrow(()-> new UserNotFoundException("this offre not found"));
    }

    public List<Offre> findAllOffres(){
        return offreRepo.findAll();
    }
    public String deleteOffre(Long id){
       // offreRepo.deleteOffreById(id);
        offreRepo.deleteById(id);
        return "L'offre "+id+ " est supprimée ...";
    }
    public Offre updateOffre(Offre offre){
        Long offreId = offre.getId();
        Offre existsOffre = offreRepo.findById(offreId).get();
        existsOffre.setCompany(offre.getCompany());
        existsOffre.setNiveau(offre.getNiveau());
        existsOffre.setProfil(offre.getProfil());
        existsOffre.setDescription(offre.getDescription());
        existsOffre.setSalaire(offre.getSalaire());
        existsOffre.setVille(offre.getVille());
        existsOffre.setTitle(offre.getTitle());
        return offreRepo.save(existsOffre);
    }

    public Offre findByProfil(String profil){
        return offreRepo.findByProfil(profil);
    }

    public List<Offre> findOffreByCompany(Company company) {
        return offreRepo.findOffreByCompany(company);
    }

    public Offre updateOffreStatus(Offre offre){
        Offre existsOffre = offreRepo.findById(offre.getId()).orElse(null);
        existsOffre.setStatus(true);
        offreRepo.save(existsOffre);
        return existsOffre;
    }


    public List<Offre> getValidatedOffres() {
        return offreRepo.getValidatedOffres();
    }

}
