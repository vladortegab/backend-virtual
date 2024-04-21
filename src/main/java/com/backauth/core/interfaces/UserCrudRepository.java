package com.backauth.core.interfaces;

import com.backauth.core.dominio.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, String> {
    Optional<User> findByUserEmail(String userEmail);
}
