--USERS
INSERT INTO users (username, email, is_Admin) VALUES
('Alice', 'alice@example.com', FALSE),
('Bob', 'bob@example.com', FALSE),
('Charlie', 'charlie@example.com', TRUE),
('Diana', 'diana@example.com', FALSE);

--URL
INSERT INTO links (user_id, original_url, short_url) VALUES
(1, 'https://www.verylongwebsiteurl.com/articles/interesting-article', 'https://short.ly/abc123'),
(1, 'https://www.anotherlongurl.com/about-us', 'https://short.ly/def456'),
(2, 'https://www.somesite.com/product/item12345', 'https://short.ly/ghi789'),
(3, 'https://www.example.com/promo', 'https://short.ly/jkl012');
