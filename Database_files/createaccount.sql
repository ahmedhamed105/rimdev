DROP PROCEDURE IF EXISTS rim_accounting.createaccount;
DELIMITER //
CREATE  PROCEDURE rim_accounting.createaccount(
	IN customer_no VARCHAR(255),  -- Transaction description
	IN customer_name VARCHAR(255),  -- Transaction description
    IN Currency VARCHAR(255),
	OUT error_code VARCHAR(255)   -- as table error_codes

)
main:BEGIN

set @cdate = now();
set @currency_id = 0;
set @acct_no=0;

   -- get currency id    
select id INTO @currency_id
 from rim_accounting.currency 
 where All_status_ID = 1 
 and Currency_ISO = Currency;
 
 if @currency_id = 0 
 then
  set error_code = 2;   -- currency error
   LEAVE main;
 END IF; 
 
 
 SELECT max(acct_number)+1 into @acct_no FROM rim_accounting.account;
   
 if @acct_no = 0 
 then
  set error_code = 3;   -- account max error
   LEAVE main;
 END IF; 

INSERT INTO `rim_accounting`.`account` (`acct_number`,`acct_name`,`Curr_balance`,`Aval_balance`,`Last_modification`,`Create_date`,`Customer_number`,`Currency_ID`,`error`,`effective_date`,`All_status_ID`)
 VALUES (@acct_no,
 customer_name,
 0.000000,
 0.000000,@cdate,@cdate,customer_no,@currency_id,'',@cdate,1);

commit; 

 set error_code = concat(1,',',@acct_no);
   
END //
 
DELIMITER ;