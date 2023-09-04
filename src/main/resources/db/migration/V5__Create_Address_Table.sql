CREATE TABLE tb_address (
	id bigserial NOT NULL,
	cep varchar(8) NULL,
	city varchar(50) NULL,
	complement varchar(150) NULL,
	"number" int4 NULL,
	state varchar(50) NULL,
	street varchar(150) NULL,
	resident_id int8 NULL,
	CONSTRAINT tb_address_pkey PRIMARY KEY (id),
	CONSTRAINT fkd5a64s6c6gyccypp978rabx8x FOREIGN KEY (resident_id) REFERENCES tb_users(id)
);
