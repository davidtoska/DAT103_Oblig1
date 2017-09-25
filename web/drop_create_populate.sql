DROP TABLE "item";

/*
' This script did not produce the tables used in this program. 
* I used the "drop and create" property in persistence.xml to generete
* new tables when the application starts up. I didn't find out how to
* create the sequence for the id in time.   
*/
CREATE TABLE item
(

   id                   INTEGER NOT NULL,
   iname		CHARACTER VARYING (40),
   PRIMARY KEY (id)
);



