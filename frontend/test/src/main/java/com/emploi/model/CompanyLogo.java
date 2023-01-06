package com.emploi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data

public class CompanyLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
    private Long company_id;

    public CompanyLogo(String fileName, String fileType, byte[] data, Long company_id) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.company_id = company_id;

    }
}
