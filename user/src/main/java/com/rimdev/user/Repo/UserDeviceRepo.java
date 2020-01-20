package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserDevice;
@Repository
public interface UserDeviceRepo extends CrudRepository<UserDevice, Integer>{

}
