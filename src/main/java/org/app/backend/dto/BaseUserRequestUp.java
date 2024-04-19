package org.app.backend.dto;


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
public class BaseUserRequestUp {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private MultipartFile image;
}

