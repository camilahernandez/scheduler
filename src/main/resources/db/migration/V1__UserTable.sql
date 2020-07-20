CREATE TABLE person (
    id UUID NOT NULL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    ph_number int NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);