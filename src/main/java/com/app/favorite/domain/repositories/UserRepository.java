package com.app.favorite.domain.repositories;

import com.app.favorite.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
