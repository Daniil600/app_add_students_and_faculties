ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age >= 16);
ALTER  TABLE student ALTER COLUMN name SET NOT NULL;
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE faculty ADD CONSTRAINT color_unique UNIQUE (name, color);
ALTER TABLE student ADD COLUMN age BOOLEAN NOT NULL DEFAULT 20;