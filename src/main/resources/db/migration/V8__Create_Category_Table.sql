CREATE TABLE tb_category (
	id bigserial NOT NULL,
	description varchar(1000) NULL,
	"name" varchar(100) NULL,
	CONSTRAINT tb_category_pkey PRIMARY KEY (id),
	CONSTRAINT uk_4gpl7afmaxiecnv7fv7unn2mp UNIQUE (name)
);