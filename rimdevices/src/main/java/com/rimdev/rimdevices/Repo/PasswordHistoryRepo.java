package com.rimdev.rimdevices.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.PasswordHistory;
@Repository
public interface PasswordHistoryRepo extends CrudRepository<PasswordHistory, Integer>{

}
