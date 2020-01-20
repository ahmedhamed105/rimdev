/**
 * 
 */
package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Adress;


@Repository
public interface AdressRepo extends CrudRepository<Adress, Integer> {

}
