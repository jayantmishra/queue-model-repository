CREATE DEFINER=`SoftCell`@`localhost` PROCEDURE `get_work`(
IN instance_id INT,
IN batch_size INT
)
BEGIN
	
DECLARE last_process_id INT;
DECLARE diff INT;
DECLARE activity_min_id INT;
DECLARE activity_max_id INT;
DECLARE row_coun INT;

SET activity_min_id = -1;
SET activity_max_id = -1;

-- getting last processed id of activity from softcell_routing_variable 
-- this table was created explicitly for this purpose only :)


SELECT routing_var_value INTO last_process_id FROM softcell_routing_variable where routing_var_name = 'LASTPROCESSID' LIMIT 1;

SET activity_max_id = (SELECT MAX(activity_id) from softcell_routing_work);

-- epic stuff here 
IF (last_process_id + batch_size > activity_max_id) THEN
	SET diff = activity_max_id - last_process_id;
    SET activity_min_id = last_process_id + 1;
    SET activity_max_id = last_process_id + diff;
else 
	SET activity_min_id = last_process_id + 1;
    SET activity_max_id = activity_min_id + batch_size -1;
END IF;

-- inserting the new batch here

INSERT INTO softcell_routing_wat (
instance_id,
min_id,
max_id,
working_status,
aborted
)
values (
instance_id,
activity_min_id,
activity_max_id,
1,
0
);


-- updating last process id in routing variable
UPDATE softcell_routing_variable 
SET routing_var_value = activity_max_id
WHERE routing_var_name = 'LASTPROCESSID';

select * from softcell_activity where activity_id >= activity_min_id
and activity_id <= activity_max_id;

SET row_coun = FOUND_ROWS();

if (row_coun = 0) then
	DELETE FROM egpl_routing_work
    where activity_id >= activity_min_id and activity_id <= activity_max_id;
    
    DELETE FROM egpl_routing_wat 
    where instance_id = instance_id;
    
end if;
END