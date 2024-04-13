package org.app.backend.User.Service;

import lombok.RequiredArgsConstructor;
import org.app.backend.User.Dto.*;
import org.app.backend.User.Model.Enum.Role;
import org.app.backend.User.Model.User;
import org.app.backend.User.repo.IntRepUser;
import org.app.backend.Util.ImgService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IntRepUser repUser;
    private final ImgService imgService;
    private final PasswordEncoder passwordEncoder;


    public UserResp myCompte(){
        UserDetails us= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=repUser.findByEmail(us.getUsername()).orElse(null);
        assert user != null;
        return new UserResp(user);
    }

    public UserResp Upd_passwrd_myaccount(Userpassword userpassword) {
        if(userpassword.getPassword().equals(userpassword.getPassword_comfirme())){
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = repUser.findByEmail(userDetails.getUsername()).orElse(null);
                user.setPassword(passwordEncoder.encode(userpassword.getPassword()));
                return new UserResp(repUser.save(user));
        }
            return null;
    }
    public Page<UserResp> list_User(Pageable pageable, String search) {
        Page<User> userpage = null;
        if(!search.isEmpty()){
            userpage=repUser.findalluser(search,search,Role.User,pageable);

        }else{
            userpage=repUser.findAll(pageable);
        }
        return  userpage.map(UserResp::new);
    }
    public UserResp find_User(Long id) {
        User user=repUser.findById(id).orElse(null);
        UserResp response = null;
        if (user != null) {
            response=new UserResp(user);
        }
        return response;
    }
    public List<UserResp> list_User_by_serch(String search) {
        List<User> users = null;
        if (search != null && !search.isEmpty()) {
            users = repUser.findalluser(search, search, Role.User);
        } else {
            users = repUser.findAll();
        }
        return users.stream()
                .map(user -> {
                    UserResp response = new UserResp();
                    response.setId(user.getId());
                    response.setEmail(user.getEmail());
                    response.setImage(user.getImage());
                    response.setRole(user.getRole());
                    response.setFirst_name(user.getFirst_name());
                    response.setLast_name(user.getLast_name());
                    return response;
                })
                .collect(Collectors.toList());
    }
    public UserResp Create_User(UserRequest req) {
        String img="";
        if(req.getImage()!=null){
            img=imgService.addimage(req.getImage(),"Users");
        }
        var user= User.builder().first_name(req.getFirstname())
                .Last_name(req.getLastname())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role((req.getRole().toLowerCase().equals(Role.Admin.name().toLowerCase()))?Role.Admin:Role.User)
                .image(img)
                .build();
        return new UserResp(repUser.save(user));
    }



    public UserResp Delete_User(Long id) {
        User user=repUser.findById(id).orElse(null);
        if(user!=null){
            if(!user.getImage().isEmpty()){
                imgService.deleteimage(user.getImage());
            }
            repUser.delete(user);
            return new UserResp(user);
        }
        return null;
    }
    /***-----------------------------**/
    public UserResp Update_User(UserRequestUp userRequest, Long id) {
        User user = repUser.findById(id).orElse(null);
        if (user != null) {
            if (!userRequest.getFirstname().isEmpty()) {
                user.setFirst_name(userRequest.getFirstname());
            }
            if (!userRequest.getLastname().isEmpty()) {
                user.setLast_name(userRequest.getLastname());
            }
            if (!userRequest.getEmail().isEmpty()) {
                user.setEmail(userRequest.getEmail());
            }
            if (!userRequest.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            }
            if (!userRequest.getRole().isEmpty()) {
                user.setRole((userRequest.getRole().toLowerCase().equals(Role.Admin.name().toLowerCase()))?Role.Admin:Role.User);
            }
            if (userRequest.getImage() != null) {
                if (!user.getImage().isEmpty()) {
                    imgService.deleteimage(user.getImage());
                }
                String newImageFileName = imgService.addimage(userRequest.getImage(), "Users");
                user.setImage(newImageFileName);
            }
            User s=repUser.save(user);
            return new UserResp(s);
        }
        return null;
    }
    public UserResp update_myaccount(BaseUserRequestUp baseUserRequestUp) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = repUser.findByEmail(userDetails.getUsername()).orElse(null);
        if (user != null) {
            if (!baseUserRequestUp.getFirstname().isEmpty()) {
                user.setFirst_name(baseUserRequestUp.getFirstname());
            }
            if (!baseUserRequestUp.getLastname().isEmpty()) {
                user.setLast_name(baseUserRequestUp.getLastname());
            }
            if (!baseUserRequestUp.getEmail().isEmpty()) {
                user.setEmail(baseUserRequestUp.getEmail());
            }
            if (baseUserRequestUp.getImage() != null) {
                if (!user.getImage().isEmpty()) {
                    imgService.deleteimage(user.getImage());
                }
                String newImageFileName = imgService.addimage(baseUserRequestUp.getImage(), "Users");
                user.setImage(newImageFileName);
            }
            User s=repUser.save(user);
            return new UserResp(s);
        }
        return null;
    }
}
