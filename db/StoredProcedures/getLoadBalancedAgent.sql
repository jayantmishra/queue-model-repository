CREATE DEFINER=`SoftCell`@`localhost` PROCEDURE `load_balance_procedure`(
IN activity_id INT,
IN department_id INT,
IN queue_id INT,
OUT final_final_agent int
)
BEGIN

DECLARE agent_id INT;
DECLARE found_agent INT;
DECLARE final_agent INT;
DECLARE dep_id INT;
DECLARE route_when INT;
DECLARE current_user_load INT;
DECLARE max_load_defined INT;
DECLARE least_load INT;
DECLARE first_index INT;
DECLARE done INT DEFAULT FALSE;
DECLARE my_cursor CURSOR FOR SELECT b.user_id, a.route_when, b.department_id
FROM temp_routing_work a, softcell_user b
where a.user_id = b.user_id;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
SET final_agent = -1;
SET found_agent = -1;
SET least_load = 0;
SET first_index = -1;
-- create temporary table to store users which are configured for load balancing on this queue 
DROP TABLE IF EXISTS temp_routing_work;


CREATE temporary TABLE temp_routing_work(
user_id int(11) not null,
route_when int(3) not null default 0
)ENGINE=InnoDB  DEFAULT CHARSET=latin1;

-- inserting into temporary table
INSERT INTO temp_routing_work(user_id, route_when) select a.user_id, a.route_when from softcell_queue_route_to a where a.queue_id = queue_id;

OPEN my_cursor;

read_loop: LOOP
FETCH my_cursor INTO agent_id,route_when,dep_id;
IF done THEN
LEAVE read_loop;
END IF;
    IF route_when = 1 then
    
        set found_agent = agent_id;
        if (found_agent > 0) then
        
            select setting_value into max_load_defined from softcell_setting where setting_name = 'user_max_load';
            
            -- Getting current user load
            SET current_user_load = (select load_count FROM softcell_user_load WHERE  user_id = agent_id);
            set current_user_load = current_user_load +1;
            
            if (current_user_load <= max_load_defined) then
             
                if( (first_index = -1) OR (current_user_load < least_load)) then
                
                    set first_index = first_index + 1; -- first time entry into this loop :)
                    
                    set least_load = current_user_load; -- assuming this being least load :|
                    set final_agent = found_agent;
                end if;
            end if;
        end if;
    end if;

END LOOP read_loop;
CLOSE my_cursor;

SET final_final_agent = final_agent;
SELECT final_final_agent;
END