package com.rimdev.accounting.Repo;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.AccountProcess;


@Repository
public interface AccountProcessRepo extends CrudRepository<AccountProcess, Integer>{


    @Procedure(name = "posttransaction")
    int main3(@Param("acct_no") String acct_no,@Param("Customer_id") String Customer_id);

}
