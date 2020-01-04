DROP PROCEDURE IF EXISTS rim_accounting.postonelegtransaction;
DELIMITER //
CREATE  PROCEDURE rim_accounting.postonelegtransaction(
	IN reference_no VARCHAR(255),  -- Transaction description
    IN Currency VARCHAR(255),
    IN acct_no VARCHAR(255),
    IN amount DECIMAL(20,6),
    IN Trantypecode integer, -- 100 debit 101 force debit 200 credit
    IN Trx_flow VARCHAR(255),  -- Cash ,... as table flow_type
    IN Trx_desc VARCHAR(255),  -- Transaction description
    IN hold_id integer,  -- Hold ID if have
    OUT error_code VARCHAR(255)   -- as table error_codes

)
main:BEGIN

set @cdate = now();

if reference_no is null then
set reference_no =rim_accounting.generateref();
END IF;

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

-- if debit sub from avalible and current 
		if @payment = 1 and Trantypecode = 100
		THEN

       set  @curr_up = @curr - amount ;
       set  @avl_up = @avl - amount ;

	if @avl_up < 0
THEN
 INSERT INTO `rim_accounting`.`account_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`,
`create_Date`,
`effective_date`)
VALUES
(
amount,
Trx_desc,
@currency_id,
@account,
@Trxtype_id,
reference_no,
@flow_id,
7,
hold_id,
@cdate,
@cdate); 
commit;
set error_code = concat(7,',',reference_no);
IF error_code > 0 THEN
         LEAVE main;
    END IF; 

ELSE

update rim_accounting.account  set Aval_balance = @avl_up,Curr_balance=@curr_up,effective_date=@cdate  where id = @account 
and Currency_ID =  @currency_id;    
commit;
INSERT INTO `rim_accounting`.`account_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`,
`create_Date`,
`effective_date`)
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
hold_id,
@cdate,
@cdate); 

commit;
	END IF; 

       END IF; 
       
       
       -- if  force debit sub from avalible and current 
		if @payment = 1 and Trantypecode = 101
		THEN

       set  @curr_up = @curr - amount ;
       set  @avl_up = @avl - amount ;

	

update rim_accounting.account  set Aval_balance = @avl_up,Curr_balance=@curr_up,effective_date=@cdate  where id = @account 
and Currency_ID =  @currency_id;    
commit;
INSERT INTO `rim_accounting`.`account_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`,
`create_Date`,
`effective_date`)
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
hold_id,
@cdate,
@cdate); 
commit;
END IF; 


-- if credit sub from avalible and current 
if @payment = 1 and Trantypecode = 200
THEN
       set  @curr_up = @curr + amount ;
       set  @avl_up = @avl + amount ;
       
update rim_accounting.account  set Aval_balance = @avl_up,Curr_balance=@curr_up,effective_date=@cdate  where id = @account 
and Currency_ID =  @currency_id;   
commit;
INSERT INTO `rim_accounting`.`account_process`
(`TRX_Amount`,
`TRX_description`,
`Currency_ID`,
`account_ID`,
`Transaction_type_ID`,
`Reference_number`,
`Flow_type_ID`,
`Error_codes_ID`,
`Hold_id`,
`create_Date`,
`effective_date`)
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
hold_id,
@cdate,
@cdate); 
commit;
END IF;  

commit;
 set error_code = concat(0,',',reference_no);   
    
   
END //
 
DELIMITER ;