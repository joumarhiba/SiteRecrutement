package com.emploi.service;

import com.emploi.model.Company;
import com.emploi.model.UserRole;
import com.emploi.repository.CompanyRepo;
import com.emploi.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements UserDetailsService{

    private final CompanyRepo companyRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Company> findAllCompanies(){
        return companyRepo.findAll();
    }

    public Company addCompany(Company company){
        company.setUserRole(UserRole.COMPANY);
        String encoderPwd = bCryptPasswordEncoder.encode(company.getPassword());
        company.setPassword(encoderPwd);
        return companyRepo.save(company);
    }


    public Company loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Company> company = companyRepo.findByEmail(email);
        return companyRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Company not found ...."));
    }

    @Override
    public UserDetails loadUserByUsername(String emailAndRole) throws UsernameNotFoundException {
        return null;
    }

}
