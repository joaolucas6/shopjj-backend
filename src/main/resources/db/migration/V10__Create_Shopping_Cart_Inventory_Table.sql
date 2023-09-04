CREATE TABLE shopping_cart_inventory (
	shopping_cart_id int8 NOT NULL,
	inventory int4 NULL,
	inventory_key int8 NOT NULL,
	CONSTRAINT shopping_cart_inventory_pkey PRIMARY KEY (shopping_cart_id, inventory_key),
	CONSTRAINT fk67a164wcp6u1ksg77fktkkxn0 FOREIGN KEY (shopping_cart_id) REFERENCES tb_shopping_cart(id),
	CONSTRAINT fk7t5vhpe0d839b4fdr2dd6hly6 FOREIGN KEY (inventory_key) REFERENCES tb_product(id)
);
