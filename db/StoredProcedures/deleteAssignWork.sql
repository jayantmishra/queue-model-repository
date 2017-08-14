CREATE DEFINER=`SoftCell`@`localhost` PROCEDURE `delete_assignment_work`(
IN instance_id INT

)
BEGIN

DECLARE min_act_id INT;
DECLARE max_act_id INT;

SELECT min_id,max_id INTO min_act_id, max_act_id from softcell_routing_assign_wat where 
instance_id = instance_id;

-- deleting from assign work table 
delete from softcell_routing_assign_work where activity_id >= min_act_id
and activity_id <= max_act_id;

-- deleting the batch 
delete from softcell_routing_assign_wat where instance_id = instance_id;
END