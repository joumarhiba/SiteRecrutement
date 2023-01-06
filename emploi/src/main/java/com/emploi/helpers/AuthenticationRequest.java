package com.emploi.helpers;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor

public class AuthenticationRequest {

    private String email;
    private String password;
    private String userRole;


}
