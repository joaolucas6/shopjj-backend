ALTER TABLE tb_users
ADD COLUMN role VARCHAR(10) NOT NULL DEFAULT 'USER';

ALTER TABLE tb_users
ADD CONSTRAINT chk_role CHECK (role IN ('USER', 'MANAGER'));