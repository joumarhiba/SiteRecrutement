package com.emploi.service;


import com.emploi.helpers.AuthenticationRequest;
import com.emploi.helpers.AuthenticationResponse;
import com.emploi.helpers.RegisterRequest;
import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.model.UserRole;
import com.emploi.repository.AdminRepo;
import com.emploi.repository.CompanyLogoRepo;
import com.emploi.repository.CompanyRepo;
import com.emploi.security.JwtHandler;
import com.emploi.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final AdminRepo adminRepo;
    private final CompanyRepo companyRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtHandler jwtHandler;
    private final AuthenticationManager authenticationManager;
    private final CompanyLogoRepo companyLogoRepo;

    public AuthenticationResponse register(RegisterRequest request) {
        if(request.getUserRole().equals(UserRole.COMPANY)) {
            var company = Company.builder()
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(request.getPassword()))
                    //.userRole(UserRole.COMPANY)
                    .userRole(request.getUserRole())
                    .build();
            companyRepo.save(company);
            System.out.println();
            var jwtToken =jwtHandler.generateToken(company);
            return AuthenticationResponse.builder().token(jwtToken)
                    .build();
       }
        if(request.getUserRole().equals(UserRole.ADMIN)) {
            var admin = Admin.builder()
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(request.getPassword()))
                    //.userRole(UserRole.COMPANY)
                    .userRole(request.getUserRole())
                    .build();
            adminRepo.save(admin);
            var jwtToken2 =jwtHandler.generateToken(admin);
            return AuthenticationResponse.builder().token(jwtToken2)
                    .build();
        }
      return  null;
    }

    public AuthenticationResponse authenticateCompany(AuthenticationRequest request) throws Exception {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(), request.getPassword()
//                )
//        );
        UserDetails company = companyRepo.findByEmail(request.getEmail()).orElseThrow();
        if(company != null ){

            var jwtToken =jwtHandler.generateToken(company);
            return AuthenticationResponse.builder().token(jwtToken)
                    .build();
        } else {
            System.out.println("this company not found");
        }

        return null;
        // var company = companyRepo.findByEmail(request.getEmail()).orElseThrow();
//        var jwtToken =jwtHandler.generateToken(company);
//        return AuthenticationResponse.builder().token(jwtToken)
//                .build();
    }


    public AuthenticationResponse authenticateAdmin(AuthenticationRequest request) throws Exception {

        UserDetails admin = adminRepo.findByEmail(request.getEmail()).orElseThrow(()->new NoSuchElementException("cet utilisateur n'est pas trouvé !!"));
        if(admin != null ){

            var jwtToken =jwtHandler.generateToken(admin);
            return AuthenticationResponse.builder().token(jwtToken)
                    .build();
        } else {
            System.out.println("this admin not found");
        }
        return null;
    }
}
