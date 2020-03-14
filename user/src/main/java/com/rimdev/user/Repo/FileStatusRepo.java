package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import com.rimdev.user.entities.FileStatus;

public interface FileStatusRepo extends CrudRepository<FileStatus, Integer>{

}
