package com.backauth.core.interfaces;

import com.backauth.core.dominio.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
