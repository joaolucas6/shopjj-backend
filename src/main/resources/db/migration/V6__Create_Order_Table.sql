CREATE TABLE tb_order (
	id bigserial NOT NULL,
	instant timestamp(6) NULL,
	order_status varchar(255) NULL,
	payment_method varchar(255) NULL,
	total_price numeric(38, 2) NULL,
	address_id int8 NULL,
	costumer_id int8 NULL,
	CONSTRAINT tb_order_order_status_check CHECK (((order_status)::text = ANY ((ARRAY['PENDING'::character varying, 'PROCESSING'::character varying, 'SHIPPED'::character varying, 'DELIVERED'::character varying, 'CANCELLED'::character varying, 'COMPLETED'::character varying])::text[]))),
	CONSTRAINT tb_order_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['CREDIT_CARD'::character varying, 'DEBIT_CARD'::character varying, 'PAYPAL'::character varying, 'APPLE_PAY'::character varying, 'GOOGLE_PAY'::character varying, 'CASH'::character varying, 'BANK_TRANSFER'::character varying])::text[]))),
	CONSTRAINT tb_order_pkey PRIMARY KEY (id),
	CONSTRAINT fk4hxxjbprv2hq26h643o3e8pcw FOREIGN KEY (address_id) REFERENCES tb_address(id),
	CONSTRAINT fkm4iwhb1wwsey0d61x30rcjwth FOREIGN KEY (costumer_id) REFERENCES tb_users(id)
);
