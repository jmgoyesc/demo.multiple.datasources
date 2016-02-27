CREATE TABLE public.first_table
(
   id integer, 
   name text, 
   CONSTRAINT pk_first_table PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public.first_table
  OWNER TO "first.user";
