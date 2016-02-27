CREATE TABLE public."third_table"
(
   id integer, 
   name character(1), 
   CONSTRAINT pk_third_table PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public."third_table"
  OWNER TO "third.user";