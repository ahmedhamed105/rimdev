package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimlang.entities.FileStatus;

public interface FileStatusRepo extends CrudRepository<FileStatus, Integer>{

}
