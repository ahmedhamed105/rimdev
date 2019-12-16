package com.rimdev.RES.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.RES.entites.Device_table;

@Repository
public interface DevicetableRepo extends CrudRepository<Device_table, Long> {

}
