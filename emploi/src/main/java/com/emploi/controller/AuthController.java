package com.emploi.controller;


import com.emploi.helpers.AuthenticationRequest;
import com.emploi.helpers.AuthenticationResponse;
import com.emploi.helpers.RegisterRequest;
import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.security.JwtHandler;
import com.emploi.service.AdminService;
import com.emploi.service.AuthService;
import com.emploi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {


    private final AuthService authService;

    @GetMapping("/auth/")
    public String gets(){
        return "hello yeeeeees we do it !!";
    }



    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }




    @PostMapping("/auth/authenticateCompany")
    public ResponseEntity<AuthenticationResponse> authenticateCompany(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authService.authenticateCompany(request));

    }

    @PostMapping("/auth/authenticateAdmin")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authService.authenticateAdmin(request));

    }



  //  @PostMapping("/auth/{role}")

//    public ResponseEntity<String> authenticate(
//            @PathVariable String role,
//            @RequestBody AuthenticationRequest authenticationRequest
//    ) {
//                authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getEmail() + ":" + role,
//                        authenticationRequest.getPassword())
//        );
//        if(role.equalsIgnoreCase("ADMIN")) {
//
//            Admin admin = adminService.loadUserByEmail(authenticationRequest.getEmail());
//            if (admin != null) {
//                return ResponseEntity.ok(jwtHandler.generateToken(admin));
//            }
//
//        }
//        else if(role.equalsIgnoreCase("COMPANY")){
//            Company company = companyService.loadUserByEmail(authenticationRequest.getEmail());
//            if(company != null) {
//                return ResponseEntity.ok(jwtHandler.generateToken(company));
//            }
//        }
//
//        return ResponseEntity.status(400).body("Error authenticating user");
//    }
}