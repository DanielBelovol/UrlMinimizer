CREATE TABLE IF NOT EXISTS users
(
  user_id  SERIAL PRIMARY KEY,
  username VARCHAR(255)        NOT NULL,
  email    VARCHAR(255) UNIQUE NOT NULL,
  isAdmin  BOOLEAN             NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS links
(
  link_id       SERIAL PRIMARY KEY,
  user_id       INT           NOT NULL,
  original_url  VARCHAR(2000) NOT NULL,
  short_url     VARCHAR(255)  NOT NULL,
  creation_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
