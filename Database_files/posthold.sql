DROP PROCEDURE IF EXISTS rim_accounting.posthold;
DELIMITER //
CREATE  PROCEDURE rim_accounting.posthold(
	IN reference_no VARCHAR(255),  -- Transaction description
    IN Currency VARCHAR(255),
    IN acct_no VARCHAR(255),
    IN amount DECIMAL(20,6),
    IN Trantypecode integer, -- 300 hold 301 reverse
    IN Trx_flow VARCHAR(255),  -- Cash ,... as table flow_type
    IN Trx_desc VARCHAR(255),  -- Transaction description
    IN holdid integer,  -- Hold ID if reverse 
    OUT error_code integer   -- as table error_codes
)
main:BEGIN

-- check  currency
select 
case when count(*) = 1 then 0 else 3 end INTO error_code
 from rim_accounting.currency 
 where Currency_status = 'Active' 
 and Currency_ISO = Currency;
 -- end if not exist  currency
IF error_code > 0 THEN
         LEAVE main;
    END IF;
   -- get currency id    
select id INTO @currency_id
 from rim_accounting.currency 
 where Currency_status = 'Active' 
 and Currency_ISO = Currency;
 
-- check  account
select 
case when count(*) = 1 then 0 else 1 end INTO error_code
from rim_accounting.account 
where 
acct_number = acct_no 
and Currency_ID =  (select ID from rim_accounting.currency where Currency_status = 'Active' and Currency_ISO = Currency);

-- end if not exist   account
IF error_code > 0 THEN
         LEAVE main;
    END IF;
    
    
 -- get account id   
select id,Aval_balance,Curr_balance INTO @account,@avl,@curr
from rim_accounting.account 
where 
acct_number = acct_no 
and Currency_ID =  (select ID from rim_accounting.currency where Currency_status = 'Active' and Currency_ISO = Currency);    

-- check flow type 
select 
case when count(*) = 1 then 0 else 5 end INTO error_code 
from rim_accounting.flow_type where Flow_status = 'Active' and Flowtype = Trx_flow ;
  -- end if not exist  flow_type
IF error_code > 0 THEN
         LEAVE main;
    END IF;  
    -- get flow type id    
select id INTO @flow_id 
from rim_accounting.flow_type where Flow_status = 'Active' and Flowtype = Trx_flow;


-- check transaction type 
select 
case when count(*) = 1 then 0 else 6 end INTO error_code 
from rim_accounting.transaction_type where TRXtype_status = 'Active' and Trxcode = Trantypecode ;
  -- end if not exist  transaction type
IF error_code > 0 THEN
         LEAVE main;
    END IF;  
    -- get transaction type id    
select id,payment_not INTO @Trxtype_id,@payment 
from rim_accounting.transaction_type where TRXtype_status = 'Active' and Trxcode = Trantypecode;

     --  hold
		if @payment = 0 and Trantypecode = 300
		THEN
        
BEGIN
       set @holdid = 0;
       set  @avl_up = @avl - amount ;
       
select IFNULL(max(id),100)  into @holdid from rim_accounting.hold_process;

set @holdid = @holdid+1 ;

update rim_accounting.account  set Aval_balance = @avl_up where acct_number = acct_no 
and Currency_ID =  @currency_id;    
commit;

INSERT INTO `rim_accounting`.`hold_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`)
VALUES
(
amount,
Trx_desc,
@currency_id,
@account,
@Trxtype_id,
reference_no,
@flow_id,
1,
@holdid); 

commit;

set error_code = @holdid;
END;
 END IF; 
       
       
        --  reverse hold
		if @payment = 0 and Trantypecode = 301
		THEN
        select TRX_Amount into @amt from rim_accounting.hold_process where Hold_id = holdid and account_ID = @account;

       set  @avl_up = @avl +@amt;

update rim_accounting.account  set Aval_balance = @avl_up where id = @account 
and Currency_ID =  @currency_id;    
commit;
INSERT INTO `rim_accounting`.`hold_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`)
VALUES
(
@amt,
Trx_desc,
@currency_id,
@account,
@Trxtype_id,
reference_no,
@flow_id,
1,
holdid); 
commit;

set error_code = holdid;

 END IF; 
    
    
   
END //
 
DELIMITER ;