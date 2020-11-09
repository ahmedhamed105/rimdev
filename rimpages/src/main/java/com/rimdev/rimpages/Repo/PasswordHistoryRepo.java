package com.rimdev.rimpages.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.PasswordHistory;
@Repository
public interface PasswordHistoryRepo extends CrudRepository<PasswordHistory, Integer>{

}
