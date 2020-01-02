
DROP PROCEDURE IF EXISTS rim_accounting.generateref;
DELIMITER //
CREATE  PROCEDURE rim_accounting.generateref(
    OUT reference_no VARCHAR(255)   -- as table error_codes
)
gen:BEGIN

    SET @year = YEAR(NOW());
    SET @month = MONTH(NOW());
    SET @day = DAY(NOW()); 
    select IFNULL(max(id),0)+1  into @max from rim_accounting.account_process; 
    
     set reference_no =  CONCAT( @year , '-' , @month, '-' , @day , '-' ,  LPAD(@max, 9, '0'));
    
   
END //
 
DELIMITER ;