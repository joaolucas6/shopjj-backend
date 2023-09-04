CREATE TABLE product_categories (
	category_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT fk2incqmxabucegvya2ji9ytfhl FOREIGN KEY (category_id) REFERENCES tb_category(id),
	CONSTRAINT fkgsymqnqga2qdjc84tj0m71u3s FOREIGN KEY (product_id) REFERENCES tb_product(id)
);
