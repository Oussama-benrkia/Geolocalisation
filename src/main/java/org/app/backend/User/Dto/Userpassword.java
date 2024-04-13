package org.app.backend.User.Dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userpassword {
    @NotEmpty
    private String password;
    @NotEmpty
    private String password_comfirme;

}

