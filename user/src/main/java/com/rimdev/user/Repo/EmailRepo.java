package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Email;
@Repository
public interface EmailRepo extends CrudRepository<Email, Integer>{

}
