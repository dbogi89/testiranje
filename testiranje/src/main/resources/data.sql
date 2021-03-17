insert into user (id_user ,first_name , active , last_name ,password ,username )values
(1,'dejan', 1,'dejan',    '$2a$10$TD29stVYWNwu7N1oMPNrrufIa0xLhav9tdx79oDaHX/Fb1nuJeqG6' ,'dejan');
INSERT INTO role(id_role,role_name) VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
INSERT INTO user_role(id_user, id_role) VALUES (1, 1);
