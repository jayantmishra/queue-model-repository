# softcell-repository
softcell project


*****************************************************************
First Step:

1) create mySql db  ( eg : CREATE DATABASE  IF NOT EXISTS `softCellDb`;)
2) create user and grant all privileges  (CREATE USER 'softadmin'@'localhost' IDENTIFIED BY 'soft123';

GRANT ALL PRIVILEGES ON * . * TO 'softadmin'@'localhost';)

3) go to hibernate mapping file in /Repository/Resource , locate hibernate.cfg.xml


        <!-- JDBC Database connection settings -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/<your dbname>?useSSL=false</property>
        <property name="connection.username"><username></property>
        <property name="connection.password"><password></property>
        
        <your dbname> -- > db name
        <username> ---> user name
        <password> ---> password 
        
        
        
 Second Step :
 Load all table schema into db just created
 
 1) all table creation schema files are placed at /Repository/db/schemaCreation, one may either manually 
 import these on workbench or use command line 
 
 
 
 Third Step : 
 Load all storedProcedure Creation scripts
 1)all storedProcedure creation scripts are placed at /Repository/db/StoredProcedure
 
 
 
 
 Fourth Step:
 above steps are sequential and necessary once done, 
 
 Go To class CsvLoader.java in package src.com.softcell.install and change ALL_CSS_FILE_PATH variable to
 location .csv files are present in this case it would be
 <Extracted repository> / db/baseCreationCSV
 
 run it, it will automatically create initial data to work with
 
 
 
 
 
 Fifth Step:
 
 Run ProcessLAuncher.java which is the sigle entry point for this whole application
 
 
 
 **********************************************************************************************
 
 brief info about base data :
 
 initial data consist of 2 agents (id 100,101) around 8 activities (softcell_activity table)
 
 queue on which they are load balanced is (queue 1 : id 100) 
 
 initial load of both agent is 3 (set in softcell_user_load)
 
 Moment you start processLauncher.java , two threads will be created and added to a thread pool
 (workallocation.java, rulesinstance.java)
 
 workallocation will create batches of 2 (batch size defined in RulesConstant.java can be changed)
 for assignment to queue and queue to user
 
 in softcell_routing_wat and softcell_routing_assign_wat after taking lock on softcell_activity
 
 batch creation logic is written in StoredProcedure(getWork, getAssignWork)
 other tables are also involved as in to store the last process activity_id etc. , one can have a look at these Scripts to kow more
 
 these batches are picked by rulesinstance one by one and processed, look in startinstance method of ruleinstance for more detail
 
(continuously query sftcell_activity table and you will realize how activities are first assigned to queue
and then from queue to agent with least load(calculated using another script getLoadBalancedAgent.sql)


 
 
 
 