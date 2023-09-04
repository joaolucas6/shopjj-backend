CREATE TABLE product_promotions (
	promotion_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT fk9kkyg048smi8bw33y9f1rfqo4 FOREIGN KEY (product_id) REFERENCES tb_product(id),
	CONSTRAINT fke9rp9i74loifn5n8fst7yh2d8 FOREIGN KEY (promotion_id) REFERENCES tb_promotion(id)
);