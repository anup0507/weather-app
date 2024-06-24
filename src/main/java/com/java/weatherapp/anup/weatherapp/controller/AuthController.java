package com.java.weatherapp.anup.weatherapp.controller;

import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.LoginRequestDTO;
import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.LogoutRequestDTO;

import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.UserDto;
import com.java.weatherapp.anup.weatherapp.exceptions.LoginParameterIsNULLException;
import com.java.weatherapp.anup.weatherapp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
   @PostMapping("/login")
   public ResponseEntity<UserDto> login(@RequestBody LoginRequestDTO request) throws LoginParameterIsNULLException {
       UserDto userDto=authService.login(request);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
   }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO request) {
        authService.logout(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
