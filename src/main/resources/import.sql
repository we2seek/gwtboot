INSERT INTO user (name, password) VALUES ('admin', 'secret'), ('user', 'secret'), ('Third User', 'secret');

INSERT INTO role (name, description) VALUES ('ROLE_ADMIN', 'System administrator'), ('ROLE_USER', 'Site customer');

INSERT INTO user_role (user_id, role_id) SELECT u.id, r.id FROM user u, role r WHERE u.name = 'admin' AND r.name = 'ROLE_ADMIN';
INSERT INTO user_role (user_id, role_id) SELECT u.id, r.id FROM user u, role r WHERE u.name = 'admin' AND r.name = 'ROLE_USER';
INSERT INTO user_role (user_id, role_id) SELECT u.id, r.id FROM user u, role r WHERE u.name = 'user' AND r.name = 'ROLE_USER';
INSERT INTO user_role (user_id, role_id) SELECT u.id, r.id FROM user u, role r WHERE u.name = 'Third User' AND r.name = 'ROLE_USER';