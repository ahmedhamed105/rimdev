
DROP PROCEDURE IF EXISTS rim_accounting.posttransaction;
DELIMITER //
CREATE  PROCEDURE rim_accounting.posttransaction(
    IN Customer_id VARCHAR(255),
    IN Currency VARCHAR(255),
    IN acct_no VARCHAR(255),
    IN amount DECIMAL(20,6),
    IN Trx_type VARCHAR(255), -- Credit Debit as table transaction_type
    IN Trx_flow VARCHAR(255),  -- Cash ,... as table flow_type
    OUT error_code integer   -- as table error_codes
)
BEGIN

select 
case when count(*) = 1 then 0 else 1 end INTO error_code
from rim_accounting.account 
where 
acct_number = acct_no 
and Currency_ID =  (select ID from rim_accounting.currency where Currency_status = 'Active' and Currency_ISO = Currency);

   
END //
 
DELIMITER ;