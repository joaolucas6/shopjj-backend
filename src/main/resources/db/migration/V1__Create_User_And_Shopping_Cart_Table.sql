CREATE TABLE tb_users (
	id bigserial NOT NULL,
	cpf varchar(255) NULL,
	email varchar(255) NULL,
	first_name varchar(50) NULL,
	gender varchar(255) NULL,
	last_name varchar(50) NULL,
	"password" varchar(255) NULL,
	shopping_cart_id int8 NULL,
	CONSTRAINT tb_users_gender_check CHECK (((gender)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying, 'OTHER'::character varying])::text[]))),
	CONSTRAINT tb_users_pkey PRIMARY KEY (id),
	CONSTRAINT uk_1n0mon2a8006tewwqf22kdxip UNIQUE (shopping_cart_id),
	CONSTRAINT uk_grd22228p1miaivbn9yg178pm UNIQUE (email),
	CONSTRAINT uk_of4vosklhlys549u3r1jtlgmh UNIQUE (cpf)
);

CREATE TABLE tb_shopping_cart (
	id bigserial NOT NULL,
	costumer_id int8 NULL,
	CONSTRAINT tb_shopping_cart_pkey PRIMARY KEY (id),
	CONSTRAINT uk_op98264b46d3jmo6bntfnl15a UNIQUE (costumer_id),
	CONSTRAINT fkavtvfybdtf9gphqi5dym1td7n FOREIGN KEY (costumer_id) REFERENCES tb_users(id)
);

ALTER TABLE tb_users
ADD CONSTRAINT fk5n2j1sg2ugws31vvfxj37sw27 FOREIGN KEY (shopping_cart_id) REFERENCES tb_shopping_cart(id)
;