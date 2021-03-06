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
    OUT error_code VARCHAR(255)   -- as table error_codes
)
main:BEGIN

set @cdate = now();

if reference_no is null OR  reference_no ='' then
set reference_no =rim_accounting.generateref();
END IF;

-- check  currency
select 
case when count(*) = 1 then 0 else 3 end INTO error_code
 from rim_accounting.currency 
 where All_status_ID = 1 
 and Currency_ISO = Currency;
 -- end if not exist  currency
IF error_code > 0 THEN
         LEAVE main;
    END IF;
   -- get currency id    
select id INTO @currency_id
 from rim_accounting.currency 
 where All_status_ID = 1 
 and Currency_ISO = Currency;
 
-- check  account
select 
case when count(*) = 1 then 0 else 1 end INTO error_code
from rim_accounting.account 
where 
acct_number = acct_no 
and Currency_ID =  (select ID from rim_accounting.currency where All_status_ID = 1 and Currency_ISO = Currency);

-- end if not exist   account
IF error_code > 0 THEN
         LEAVE main;
    END IF;
    
    
 -- get account id   
select id,Aval_balance,Curr_balance INTO @account,@avl,@curr
from rim_accounting.account 
where 
acct_number = acct_no 
and Currency_ID =  (select ID from rim_accounting.currency where All_status_ID = 1 and Currency_ISO = Currency);    

-- check flow type 
select 
case when count(*) = 1 then 0 else 5 end INTO error_code 
from rim_accounting.flow_type where All_status_ID = 1 and Flowtype = Trx_flow ;
  -- end if not exist  flow_type
IF error_code > 0 THEN
         LEAVE main;
    END IF;  
    -- get flow type id    
select id INTO @flow_id 
from rim_accounting.flow_type where All_status_ID = 1 and Flowtype = Trx_flow;


-- check transaction type 
select 
case when count(*) = 1 then 0 else 6 end INTO error_code 
from rim_accounting.transaction_type where All_status_ID = 1 and Trxcode = Trantypecode ;
  -- end if not exist  transaction type
IF error_code > 0 THEN
         LEAVE main;
    END IF;  
    -- get transaction type id    
select id,payment_not INTO @Trxtype_id,@payment 
from rim_accounting.transaction_type where All_status_ID = 1 and Trxcode = Trantypecode;

-- check hold id if release
     if Trantypecode = 301
       THEN
       if holdid = 0 or holdid = null
       THEN
        set  error_code = '9';
       END IF; 
       
       select count(*) into @hold from rim_accounting.hold_process where Hold_id = holdid;
       
        if @hold <= 0 or @hold > 1
       THEN
        set  error_code = '9';
       END IF; 
       
     
        IF error_code > 0 THEN
         LEAVE main;
		END IF;  
END IF;  
     --  hold
		if @payment = 0 and Trantypecode = 300
		THEN
        
BEGIN
       set @holdid = 0;
       set  @avl_up = @avl - amount ;
       
select IFNULL(max(id),100)  into @holdid from rim_accounting.hold_process;

set @holdid = @holdid+1 ;

update rim_accounting.account  set Aval_balance = @avl_up,effective_date=@cdate where id = @account 
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
`Hold_id`,
`create_Date`,
`effective_date`,
`Hold_Status`)
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
@holdid,
@cdate,
@cdate,
'Active'); 

commit;


 set error_code = concat(@holdid,',',reference_no);
END;
 END IF; 
       
  
   
        --  reverse hold
		if @payment = 0 and Trantypecode = 301 and holdid <> 0
		THEN
        select TRX_Amount,id into @amt,@hold_curr from rim_accounting.hold_process where Hold_id = holdid and account_ID = @account;

       set  @avl_up = @avl +@amt;

update rim_accounting.account  set Aval_balance = @avl_up,effective_date=@cdate  where id = @account 
and Currency_ID =  @currency_id;    
commit;
update rim_accounting.hold_process  set Hold_Status = 'Closed',effective_date=@cdate  where id = @hold_curr;    
commit;

 set error_code = concat(@holdid,',',reference_no); 
 END IF; 
    
    
   
END //
 
DELIMITER ;