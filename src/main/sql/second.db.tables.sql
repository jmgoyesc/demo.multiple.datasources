CREATE TABLE public.second_table
(
   id integer, 
   name text, 
   CONSTRAINT pk_second_table PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public.second_table
  OWNER TO "second.user";