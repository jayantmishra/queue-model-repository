CREATE DEFINER=`SoftCell`@`localhost` PROCEDURE `deleteWork`(
IN instance_id INT
)
BEGIN

DECLARE min_act_id INT;
DECLARE max_act_id INT;

-- Getting min and max ids to delete as they have already been processed
SELECT min_id,max_id INTO min_act_id,max_act_id FROM softcell_routing_wat
where instance_id = instance_id; 

-- DELETE FROM softcell_routing_work

DELETE FROM softcell_routing_work where activity_id >= min_act_id AND
activity_id <= max_act_id;

-- deleting the batch :)
DELETE FROM softcell_routing_wat where instance_id = instance_id;

END