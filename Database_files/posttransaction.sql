
DROP PROCEDURE IF EXISTS rim_accounting.posttransaction;
DELIMITER //
CREATE  PROCEDURE rim_accounting.posttransaction(
    IN acct_no VARCHAR(255),
    IN Customer_id VARCHAR(255),
    OUT error_code integer
)
BEGIN
   
  SET  error_code = 2;
   
END //
 
DELIMITER ;