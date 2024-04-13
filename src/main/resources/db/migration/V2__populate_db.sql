INSERT INTO users (username, password, email, created_date)
VALUES ('admin', '$2a$10$.IeRI/Gy/8UscmtMmMHyDe2PDe0TMLn.9vb6WUSS2FVtzGmEZzcj2', 'admin@gmail.com',  NOW()),
       ('auth user', '$2a$10$uZ/R0BFKt2MqlybEAcO2NebJkdp5qWI3G2SnssNn3HcMHEZAC61Nu', 'auth_user@gmail.com',  NOW()),
       ('user', '$2a$10$AcO2NebJkdp5qWI3dp50TMLn.9vbHcMHEZRI/Gy/8Uscmtgfds54', 'user@gmail.com',  NOW());

INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');


INSERT INTO user_roles (user_id, role_id) VALUES
   ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ADMIN')),
   ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'USER'));


