package com.univer.store.controller;


import com.univer.store.entity.UserEntity;
import com.univer.store.model.request.UsuarioRequest;
import com.univer.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/login")
public class LoginController {

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
    @PostMapping("/validate")
    public ResponseEntity<Boolean> login(@RequestBody UsuarioRequest request){
        UserEntity  response = this.repository.checkLogin(request.getUsuario(), request.getPassword());

        if (response != null){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

}
