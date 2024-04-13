package org.app.backend.User.Dto;


import jakarta.validation.constraints.Email;
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
public class UserRequestUp {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String Role;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    private MultipartFile image;
}

