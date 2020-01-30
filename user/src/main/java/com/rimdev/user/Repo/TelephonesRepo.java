package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
@Repository
public interface TelephonesRepo extends CrudRepository<Telephones, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.telephones where phone_no =?1" , nativeQuery = true)
	Optional<Telephones> findbytele(String tele);


}
