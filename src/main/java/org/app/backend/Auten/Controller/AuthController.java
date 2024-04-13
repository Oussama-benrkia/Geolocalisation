package org.app.backend.Auten.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.backend.Auten.Dto.AuthenticaReq;
import org.app.backend.Auten.Dto.AuthenticationResponse;
import org.app.backend.Auten.Service.AuthenticationService;
import org.app.backend.User.Dto.BaseUserRequest;
import org.app.backend.User.repo.IntRepUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService service;
    private final IntRepUser rep;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
           @Valid @ModelAttribute BaseUserRequest req
    ){
        if (rep.existsByEmail(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticationResponse.builder().message("Email already exists").token(null)
                            .build());
        }
        return ResponseEntity.ok(service.register(req));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
           @Valid @RequestBody AuthenticaReq req
    ){
        return ResponseEntity.ok(service.authenticate(req));
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
}
