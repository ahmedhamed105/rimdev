package com.rimdev.accounting.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rimdev.accounting.Enttities.ErrorCodes;

@Repository
public interface ErrorCodesRepo extends CrudRepository<ErrorCodes, Integer>{
	
	@Query(value ="select * from rim_accounting.error_codes  where error_code = ?1" , nativeQuery = true)
	Optional<ErrorCodes> findbyiderrorcode(Integer error_code);

}
