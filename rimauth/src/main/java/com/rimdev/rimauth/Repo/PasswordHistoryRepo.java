package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.PasswordHistory;
@Repository
public interface PasswordHistoryRepo extends CrudRepository<PasswordHistory, Integer>{

}