package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.PasswordHistory;
@Repository
public interface PasswordHistoryRepo extends CrudRepository<PasswordHistory, Integer>{

}
