package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserLogin;
@Repository
public interface UserLoginRepo extends CrudRepository<UserLogin, Integer>{

}