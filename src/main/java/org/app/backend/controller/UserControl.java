package org.app.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.backend.dto.BaseUserRequest;
import org.app.backend.dto.BaseUserRequestUp;
import org.app.backend.dto.UserResp;
import org.app.backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserControl {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResp>>list_all_User(
            @RequestParam(defaultValue = "") String search
    ){
        List<UserResp> posts = userService.list_User_by_serch(search);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResp>Show_user(
            @PathVariable("id") Long id
    ){
        UserResp response=userService.find_User(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/pagination")
    public ResponseEntity<Page<UserResp>>paginate_all_User(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "")String search
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResp> posts = userService.list_User(pageable,search);
        return ResponseEntity.ok(posts);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodAr(
            MethodArgumentNotValidException exp
    ){
        var errors=new HashMap<String , String>();
        exp.getBindingResult().getAllErrors()
                .forEach(objectError ->
                {
                    var fieldName=((FieldError)objectError).getField();
                    var errorMes=objectError.getDefaultMessage();
                    errors.put(fieldName,errorMes);

                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

@PostMapping
    public ResponseEntity<UserResp> Create_User(@Valid @ModelAttribute BaseUserRequest userRequest){
    UserResp response = userService.Create_User(userRequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResp> Update_Heb(@PathVariable("id") Long id, @Valid @ModelAttribute BaseUserRequestUp userRequest){
        UserResp response = userService.Update_User(userRequest,id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResp> Delete_Heb(@PathVariable("id") Long id){
        UserResp response = userService.Delete_User(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
