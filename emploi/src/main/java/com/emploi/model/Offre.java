package com.emploi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String profil;
    private String ville;
    private String niveau;
    private Long salaire;
    private Boolean status = false;


    @ManyToOne
    @JsonIgnore
    private Admin admin;

    @ManyToOne
    @JsonIgnore
    private Company company;
}
