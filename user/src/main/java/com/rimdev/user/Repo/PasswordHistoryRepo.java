package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.PasswordHistory;
@Repository
public interface PasswordHistoryRepo extends CrudRepository<PasswordHistory, Integer>{

}