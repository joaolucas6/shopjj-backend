CREATE TABLE tb_review (
	id bigserial NOT NULL,
	commentary varchar(2000) NULL,
	rating float8 NULL,
	author_id int8 NULL,
	product_id int8 NULL,
	CONSTRAINT tb_review_pkey PRIMARY KEY (id),
	CONSTRAINT fk74u7bjsgeumi1fvhy36jukgru FOREIGN KEY (product_id) REFERENCES tb_product(id),
	CONSTRAINT fkb7ykqbbv1l2hnff8yepuj3raj FOREIGN KEY (author_id) REFERENCES tb_users(id)
);