package com.java.weatherapp.anup.weatherapp.services;

import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.LoginRequestDTO;
import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.LogoutRequestDTO;
import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.UserDto;
import com.java.weatherapp.anup.weatherapp.exceptions.LoginParameterIsNULLException;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.Session;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.UserRole;
import com.java.weatherapp.anup.weatherapp.repositories.authrepositories.SimpleTokenBasedAuthRepos.UserDataRepository;

import org.springframework.stereotype.Service;

import java.nio.channels.SeekableByteChannel;
import java.util.Random;

@Service
public class AuthService {
    private UserDataRepository userDataRepository;
    public AuthService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }
    public UserDto login(LoginRequestDTO loginRequestDTO) throws LoginParameterIsNULLException {
       // UserDto userDto = new UserDto();
        if(ValidateRequestDTData(loginRequestDTO)) {
            UserRole userRole=userDataRepository.CreateUserRole(loginRequestDTO);
            Session session=userDataRepository.createSessionForUser(userRole);

            userDataRepository.adduserSessioninMap(userRole,session);
            return createUserDTO(userRole,session);
        }
        else {
            throw new LoginParameterIsNULLException("One of the required parameters does not have value. Please note we require username,email, password and FullName for login successfully.");
        }
       // ResponseEntity<UserDto> response=new ResponseEntity<>(userDto, HttpStatus.OK);
     //   return userDto;
    }
    private UserDto createUserDTO(UserRole userRole,Session session) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userRole.getUser().getEmail());
        userDto.setToken(session.getToken());
        userDto.setRoles(userRole.getRoles());
        return userDto;
    }

    private boolean ValidateRequestDTData(LoginRequestDTO loginRequestDTO) {
        if(loginRequestDTO.getUsername() == null || loginRequestDTO.getPassword() == null
            || loginRequestDTO.getEmail()==null || loginRequestDTO.getUsername()==null
        ) {
            return false;
        }
        return true;
    }

    public void logout(LogoutRequestDTO logoutRequestDTO)
    {
        if(logoutRequestDTO.getToken().isBlank() || logoutRequestDTO.getToken().isEmpty())return;
        userDataRepository.removeUserinSessionMaps(logoutRequestDTO.getToken());
        return ;
    }
    public boolean ValidateToken(String token)
    {
        if(token == null || token.isEmpty())return false;
        if(this.userDataRepository.ValidateToken(token))return true;
        return false;

    }

}
