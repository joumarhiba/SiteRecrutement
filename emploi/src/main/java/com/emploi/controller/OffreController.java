package com.emploi.controller;

import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Offre")
@RequiredArgsConstructor

public class OffreController {
    private final OffreService offreService;


    @GetMapping("/validatedOffres")
    public ResponseEntity<List<Offre>> getValidatedOffres(){
        List<Offre> validatedOffres = offreService.getValidatedOffres();
        return new ResponseEntity<>(validatedOffres, HttpStatus.OK);
    }

     @GetMapping("/all")
    public ResponseEntity<List<Offre>> getAllOffres(){
        List<Offre> offres = offreService.findAllOffres();
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable("id") Long id){
        Offre offre = offreService.findOffreById(id);
        return new ResponseEntity<>(offre, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Offre> addOffre(@RequestBody Offre offre){
        Offre saveOffre = offreService.addOffre(offre);
        return new ResponseEntity<>(saveOffre, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Offre> updateOffre(@RequestBody Offre offre){
        Offre updateOffre = offreService.updateOffre(offre);
        return new ResponseEntity<>(updateOffre, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOffre(@PathVariable("id") Long id){
       String delete = offreService.deleteOffre(id);
        return delete;
    }

    @GetMapping("/search/{profil}")
    public Offre searchOffre(@PathVariable("profil") String profil){
            return offreService.findByProfilContaining(profil);
    }



    @PostMapping("/companyOffres")
    public List<Offre> findOffreByCompany(@RequestBody Company company){
        return offreService.findOffreByCompany(company);
    }


    @PutMapping("/updateStatus")
    public Offre updateOffreStatus(@RequestBody Offre offre){
        return offreService.updateOffreStatus(offre);
    }
}
