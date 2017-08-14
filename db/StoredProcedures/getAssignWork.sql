CREATE DEFINER=`SoftCell`@`localhost` PROCEDURE `getAssignmentWork`(
IN batch_size INT,
IN instance_id INT
)
BEGIN

DECLARE activity_min_id INT;
DECLARE activity_max_id INT;

DECLARE dept_id INT;
DECLARE activity_status int;
DECLARE activity_sub_status INT;
DECLARE count INT;
DECLARE total_count INT;
DECLARE done INT default 0;
SET activity_min_id = -1;
SET activity_max_id = -1;
SET activity_status = 4000;
SET activity_sub_status = 4100;

-- Deleting any activity lying in assign work table
DELETE FROM softcell_routing_assign_work;

-- Deleting unfinshed batch from wat table
DELETE FROM softcell_routing_assign_wat;

-- create temporary table to hold all activities in 4000 state 
DROP TABLE IF EXISTS temp_routing_work;

CREATE temporary TABLE temp_routing_work(
	activity_id int(11) not null,
    activity_status int(11) not null,
    activity_sub_status int(11) not null,
    assigned_to int(11) not null,
    queue_id int(11) not null,
    department_id int(11) not null
)ENGINE=InnoDB  DEFAULT CHARSET=latin1;


INSERT INTO temp_routing_work
SELECT a.activity_id
		, a.activity_status
        , a.activity_sub_status
        , a.assigned_to
        , a.queue_id
        , a.department_id
FROM softcell_activity a INNER JOIN softcell_queue c
ON a.queue_id = c.queue_id
AND a.activity_status = activity_status
AND a.activity_sub_status = activity_sub_status
AND a.assigned_to =  -1
and c.queue_routing_type <> 0;

SELECT 
     activity_id into activity_min_id
FROM
    temp_routing_work
ORDER BY activity_id ASC
LIMIT 1;
SELECT 
     activity_id into activity_max_id
FROM
    temp_routing_work
ORDER BY activity_id DESC
LIMIT 1;
set done = 0;

-- adding entries in routing_assing_work
INSERT INTO softcell_routing_assign_work (activity_id, department_id)
select activity_id, department_id from  temp_routing_work ;

-- add entry in assign _wat

insert into softcell_routing_assign_wat values(
	instance_id,
    activity_min_id,
    activity_max_id,
	1,
    0
);

SELECT 
    a.activity_id,
    a.department_id,
    b.queue_id,
    b.activity_status,
    b.activity_sub_status,
    b.assigned_to
FROM
    temp_routing_work a
        INNER JOIN
    softcell_activity b ON a.activity_id = b.activity_id
WHERE
    b.activity_status = 4000
        AND b.activity_sub_status = 4100
        order by a.activity_id;

END