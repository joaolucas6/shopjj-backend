CREATE TABLE tb_coupon (
	id bigserial NOT NULL,
	description varchar(1000) NULL,
	"name" varchar(30) NULL,
	percentage float8 NULL,
	validity timestamp(6) NULL,
	CONSTRAINT tb_coupon_pkey PRIMARY KEY (id),
	CONSTRAINT uk_kt871pnfsvx9gvyu9prf5j6aw UNIQUE (name)
);
