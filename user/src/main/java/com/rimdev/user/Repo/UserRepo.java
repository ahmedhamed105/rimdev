package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.User;
@Repository
public interface UserRepo extends CrudRepository<User, Integer>{

}
