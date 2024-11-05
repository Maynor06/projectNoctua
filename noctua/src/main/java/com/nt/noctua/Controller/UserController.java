package com.nt.noctua.Controller;

import com.nt.noctua.dto.UserDTO;
import com.nt.noctua.dto.UserRegisterDTO;
import com.nt.noctua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        UserDTO userDTO = userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> listUser = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        UserDTO userDTO = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/Profile/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("EditPerfil/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id){
        UserDTO userDTO1 = userService.updateUser(userDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO1);
    }

    @DeleteMapping("deleteProfile/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
