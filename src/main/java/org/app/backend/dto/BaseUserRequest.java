package org.app.backend.dto;

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
public class BaseUserRequest {
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotNull
    private MultipartFile image;
}
