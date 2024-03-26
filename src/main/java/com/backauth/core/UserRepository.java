package com.backauth.core;

import com.backauth.core.interfaces.UserCrudRepository;
import com.backauth.core.dominio.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;

    public Optional<User> getUser(int userId)
    {
        return userCrudRepository.findById(userId);
    }

    public User save(User user)
    {
        return (User) userCrudRepository.save(user);
    }

    public void delete(int userId)
    {
        userCrudRepository.deleteById(userId);
    }
}
