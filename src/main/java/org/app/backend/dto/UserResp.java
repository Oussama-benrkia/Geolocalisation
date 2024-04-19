package org.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.backend.model.enumm.Role;
import org.app.backend.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResp {
    public UserResp(User us){
        this.id= us.getId();
        this.first_name=us.getFirst_name();
        this.Last_name=us.getLast_name();
        this.email= us.getEmail();
        this.role=us.getRole();
        this.image=us.getImage();
    }
    private Long id;
    private String first_name;
    private String Last_name;
    private String email;
    private Role role;
    private String image;
}
