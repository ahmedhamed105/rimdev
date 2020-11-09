package com.rimdev.rimpages.Repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.Adress;
import com.rimdev.rimpages.entities.Email;


@Repository
public interface AdressRepo extends CrudRepository<Adress, Integer> {
	
	@Query(value ="SELECT * FROM rim_user.adress where Ad_name = ?1" , nativeQuery = true)
	Optional<Adress> findbyaddress(String address);


}
