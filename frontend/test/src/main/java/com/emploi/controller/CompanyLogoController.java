package com.emploi.controller;

import com.emploi.model.Company;
import com.emploi.model.CompanyLogo;
import com.emploi.service.CompanyLogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("Company")
@RestController
@RequiredArgsConstructor

public class CompanyLogoController {

    private final CompanyLogoService companyLogoService;

    @PostMapping("/upload")
    public CompanyLogo uploadFile(@RequestParam("image") MultipartFile image, Company company) throws Exception {
        CompanyLogo attachment = null;
        String downloadUrl= "";
        attachment = companyLogoService.saveAttachment(image, company);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/Company/download/").path(attachment.getFileName())
                .toUriString();
        return new CompanyLogo(
                attachment.getFileName(),image.getContentType(), image.getBytes(), attachment.getCompany_id()
        );
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws Exception {
        CompanyLogo file = null;
        file = companyLogoService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file; filename=\"" +file.getFileName()+"\"" )
                .body(new ByteArrayResource(file.getData()));
    }

}
