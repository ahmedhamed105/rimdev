set @ref = '2020-1-2-000000003';
-- hold
CALL `rim_accounting`.`posthold`(@ref,'EGP', '1000000001',100, 300, 'cash','hamed',null,@hold);
SELECT @hold;
-- reverse
CALL `rim_accounting`.`posthold`(@ref,'EGP', '1000000001',100, 301, 'cash','hamed',@hold,@error);
SELECT @error;
-- credit
CALL `rim_accounting`.`postonelegtransaction`(null,'EGP', '1000000001',100, 200, 'cash','hamed',null,@error);
SELECT @error;

-- Debit
CALL `rim_accounting`.`postonelegtransaction`(@ref,'EGP', '1000000001',100, 100, 'cash','hamed',null,@error);
SELECT @error;

-- force Debit
CALL `rim_accounting`.`postonelegtransaction`(@ref,'EGP', '1000000001',100, 101, 'cash','hamed',null,@error);
SELECT @error;

-- create account
CALL `rim_accounting`.`createaccount`('23232323','mohamed ahmed','EGP',@acct,@error);
SELECT @acct,@error;