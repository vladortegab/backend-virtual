package com.backauth.aplicacion;

import com.backauth.core.UserRepository;
import com.backauth.core.dominio.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(int userId)
    {
        return userRepository.getUser(userId);
    }

    public ResponseEntity<User> save(User user)
    {
        if(!userRepository.getUser(user.getUserId()).isPresent()){
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<User> update(User user){
        if(userRepository.getUser(user.getUserId()).isPresent()){
            return new ResponseEntity<>(userRepository.update(user), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public boolean delete(int userId)
    {
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }
}
