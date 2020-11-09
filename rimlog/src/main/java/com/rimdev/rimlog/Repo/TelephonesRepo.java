package com.rimdev.rimlog.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.Email;
import com.rimdev.rimlog.entities.Telephones;
@Repository
public interface TelephonesRepo extends CrudRepository<Telephones, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.telephones where phone_no =?1" , nativeQuery = true)
	Optional<Telephones> findbytele(String tele);
	
	@Query(value ="SELECT * FROM rim_user.telephones where User_login_ID =?1" , nativeQuery = true)
	Iterable<Telephones> findbyuser(int userid);


}
