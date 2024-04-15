CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        username VARCHAR(255),
        email VARCHAR(255),
        password VARCHAR(255),
        created_date DATE
);

CREATE TABLE url_mapping (
        id SERIAL PRIMARY KEY,
        url VARCHAR(255),
        short_url VARCHAR(255),
        count_of_calls INT,
        user_id BIGINT,
        FOREIGN KEY (user_id) REFERENCES users(id)
);


