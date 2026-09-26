package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.JwtService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<User> getallusers(){
        return userService.getallusers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getuserbyid(@PathVariable Integer id){
        User user=userService.getuserbyid(id).orElse(null);
        if (user==null){
            return ResponseEntity.status(404).body(new ErrorResponse("User not found"));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User createuser(@Valid @RequestBody User user){
        return userService.createuser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateuser(@PathVariable Integer id,@RequestBody User user) {
        Optional<User> updateuser = userService.updateuser(id, user);

        if (updateuser.isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorResponse("user not found"));
        }
        return ResponseEntity.ok(updateuser.get());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
       Optional<User> user= userService.login(loginRequest.getEmail(),loginRequest.getPassword());
       if (user.isEmpty()){
           return ResponseEntity.status(401).body(new ErrorResponse("Invalid email or password."));
       }
       String token=jwtService.generatetoken(user.get().getEmail());
       return ResponseEntity.ok(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable Integer id){
        boolean deleted=userService.deleteuser(id);
        if (!deleted){
            return ResponseEntity.status(404).body(new ErrorResponse("user not found"));

        }
        return ResponseEntity.ok("user deleted successfully");
    }

}
