package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getallusers(){

        return userRepository.findAll();
    }

    public Optional<User> getuserbyid(Integer id){

        return userRepository.findById(id);
    }

    public User createuser(User user){
        Cart cart=new Cart();
        user.setCart(cart);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> updateuser(Integer id,User user){

        return userRepository.findById(id).map(existing ->{
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setPassword(passwordEncoder.encode(user.getPassword()));

            return userRepository.save(existing);
        });
    }

    public Optional<User> login(String email,String password){
        Optional<User> user= userRepository.findByEmail(email);

        if (user.isEmpty()){
            return Optional.empty();
        }

        if (!passwordEncoder.matches(password,user.get().getPassword())){
            return Optional.empty();
        }
        return user;

    }

    public boolean deleteuser(Integer id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
