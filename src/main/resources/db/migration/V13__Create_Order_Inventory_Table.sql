CREATE TABLE order_inventory (
	order_id int8 NOT NULL,
	inventory int4 NULL,
	inventory_key int8 NOT NULL,
	CONSTRAINT order_inventory_pkey PRIMARY KEY (order_id, inventory_key),
	CONSTRAINT fkaemtgvxbdyjfrs2vfpo6xo84q FOREIGN KEY (inventory_key) REFERENCES tb_product(id),
	CONSTRAINT fkrj2h7e4itnvyuqvdk73xan9nv FOREIGN KEY (order_id) REFERENCES tb_order(id)
);