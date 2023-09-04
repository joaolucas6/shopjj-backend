CREATE TABLE tb_product (
	id bigserial NOT NULL,
	available_quantity int4 NULL,
	description varchar(3000) NULL,
	image_url varchar(255) NULL,
	"name" varchar(130) NULL,
	price numeric(38, 2) NULL,
	CONSTRAINT tb_product_pkey PRIMARY KEY (id),
	CONSTRAINT uk_lovy3681ry0dl5ox28r6679x6 UNIQUE (name)
);