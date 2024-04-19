package com.backauth.presentacion.controller;

import com.backauth.aplicacion.ServiceUser;
import com.backauth.core.dominio.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ControlUser {
    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int userId)
    {
        return serviceUser.getUser(userId);
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user)
    {
        return serviceUser.save(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return serviceUser.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int userId)
    {
        return serviceUser.delete(userId);
    }
}
