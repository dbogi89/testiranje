INSERT INTO role(id_role,role_name) VALUES (3,'ROLE_ADMIN1'),(4,'ROLE_USER1');
INSERT INTO user(id_user,username, first_name, last_name,password, active)VALUES (5,'dejan3456', 'dejan3456','dejan' '$2a$10$TD29stVYWNwu7N1oMPNrrufIa0xLhav9tdx79oDaHX/Fb1nuJeqG6',1);
INSERT INTO user_role(id_user, id_role) VALUES (5, 4);