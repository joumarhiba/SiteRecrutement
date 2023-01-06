package com.emploi.service;

import com.emploi.model.Company;
import com.emploi.model.CompanyLogo;
import com.emploi.repository.CompanyLogoRepo;
import com.emploi.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyLogoService {

    private final CompanyLogoRepo companyLogoRepo;
    private final CompanyRepo companyRepo;

    public CompanyLogo saveAttachment(MultipartFile file, Company company) throws Exception {
        Company existsCompany = companyRepo.findById(company.getId()).orElseThrow(()->new Exception("company not found with id = "+company.getId()));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid");
            }
            CompanyLogo logo = new CompanyLogo(fileName, file.getContentType(), file.getBytes(), existsCompany.getId());
            return companyLogoRepo.save(logo);
        } catch (Exception e) {
            throw new Exception("could not  save the file "+fileName);
        }
    }

    public CompanyLogo getFile(Long fileId) throws Exception {
        return companyLogoRepo.findById(fileId).orElseThrow(()->new Exception("file not found with id = "+fileId));
    }
}
