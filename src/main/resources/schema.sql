DROP TABLE IF EXISTS cars;

CREATE TABLE cars (
                      id UUID PRIMARY KEY,
                      brand VARCHAR(255),
                      model VARCHAR(255),
                      color VARCHAR(255),
                      registration_number VARCHAR(255) UNIQUE NOT NULL,
                      model_year INTEGER,
                      price DECIMAL NOT NULL
);