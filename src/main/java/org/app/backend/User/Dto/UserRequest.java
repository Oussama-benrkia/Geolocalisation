package org.app.backend.User.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String password;
    @NotNull
    private String Role;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private MultipartFile image;
}

