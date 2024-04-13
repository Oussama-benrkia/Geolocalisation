package org.app.backend.Auten.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthenticaReq {
@NotEmpty
@Email
    private String email;
    @NotEmpty
    private String password;
}
