package com.nt.noctua.service;

import com.nt.noctua.Model.ModelUser;
import com.nt.noctua.dto.UserDTO;
import com.nt.noctua.dto.UserRegisterDTO;
import com.nt.noctua.repository.UserRepository;
import com.nt.noctua.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JwtUtils jwtUtils;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail() )){
            throw new IllegalArgumentException("El nombre o email ya estan en uso");
        }

        ModelUser user = new ModelUser();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        String token = jwtUtils.genereteAccesToken(userRegisterDTO.getUsername());

        return token;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO getUserByUsername(String username) {
        ModelUser user = userRepository.findByEmail(username);
        if(user == null){
            throw new IllegalArgumentException("usuario no encontrado");
        }
        return new UserDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO, String id) {
        ModelUser user = userRepository.findById(Long.parseLong(id) ).orElseThrow(() -> new IllegalArgumentException("usuario no encontrado"));
        user.setBio(userDTO.getBio());
        user.setProfileImagenUrl(userDTO.getProfileImagenUrl());
        user = userRepository.save(user);

        return new UserDTO(user);
    }

    public UserDTO getUserById(Long id) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("usuario no encontrado"));
        return new UserDTO(user);
    }

    public void deleteUser(String id) {
        if(!userRepository.existsById(Long.parseLong(id))){
            throw new IllegalArgumentException("usuario no encontrado");
        }
        userRepository.deleteById(Long.parseLong(id));
    }


}
