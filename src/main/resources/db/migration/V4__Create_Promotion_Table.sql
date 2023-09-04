CREATE TABLE tb_promotion (
	id bigserial NOT NULL,
	description varchar(1000) NULL,
	end_date timestamp(6) NULL,
	percentage float8 NULL,
	start_date timestamp(6) NULL,
	CONSTRAINT tb_promotion_pkey PRIMARY KEY (id)
);
