CREATE TABLE tb_order_coupons (
	order_id int8 NOT NULL,
	coupons_id int8 NOT NULL,
	CONSTRAINT uk_ddy6akh72ho278smmh6y26ucv UNIQUE (coupons_id),
	CONSTRAINT fka37q2pjk4j7lhrqqnhwlhmpfg FOREIGN KEY (coupons_id) REFERENCES tb_coupon(id),
	CONSTRAINT fkjwr9l0kd8dfjsj8k38s9tfire FOREIGN KEY (order_id) REFERENCES tb_order(id)
);