package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Telephones;
@Repository
public interface TelephonesRepo extends CrudRepository<Telephones, Integer>{

}
