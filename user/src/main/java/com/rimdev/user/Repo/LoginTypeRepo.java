package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.user.entities.LoginType;

public interface LoginTypeRepo  extends CrudRepository<LoginType, Integer> {

}
