ALTER TABLE custom_design MODIFY COLUMN image MEDIUMBLOB;

ALTER TABLE role ADD CONSTRAINT unique_role_name UNIQUE (role_name);
ALTER TABLE user ADD CONSTRAINT unique_role_id UNIQUE (role_id);