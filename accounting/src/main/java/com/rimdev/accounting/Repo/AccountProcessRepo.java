package com.rimdev.accounting.Repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.AccountProcess;


@Repository
public interface AccountProcessRepo extends CrudRepository<AccountProcess, Integer>{


    @Procedure(name = "postonelegtransaction")
    String main3(
   @Param("reference_no")String reference_no,
   @Param("Currency")String Currency,
   @Param("acct_no")String acct_no,
   @Param("amount")BigDecimal amount,
   @Param("Trantypecode")int Trantypecode,
   @Param("Trx_flow")String Trx_flow,
   @Param("Trx_desc")String Trx_desc,
   @Param("hold_id")int hold_id);

}
