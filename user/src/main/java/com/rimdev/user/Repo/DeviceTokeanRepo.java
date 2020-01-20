package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceTokean;
@Repository
public interface DeviceTokeanRepo extends CrudRepository<DeviceTokean, Integer>{

}
