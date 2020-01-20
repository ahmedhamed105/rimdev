package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserType;
@Repository
public interface UserTypeRepo extends CrudRepository<UserType, Integer>{

}
