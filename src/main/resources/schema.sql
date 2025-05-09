DROP TABLE IF EXISTS slogans;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    agree_terms BOOLEAN
);

CREATE TABLE slogans (
    id IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
