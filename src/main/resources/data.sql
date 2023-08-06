INSERT INTO company (location, name) VALUES ('Finland, Vantaa 56 tie', 'GXO Logistics');

INSERT INTO user (name, surname, phone_number, company_id, user_type) VALUES ('John', 'Doe', '+372 568 250 058', 1, 'Employer');

INSERT INTO user (name, surname, phone_number, employer_id, user_type) VALUES ('Pärt', 'Raudma', '+372 568 250 000', 1, 'Driver');
INSERT INTO user (name, surname, phone_number, employer_id, user_type) VALUES ('Tauno', 'Berg', '+372 577 251 111', 1, 'Driver');
INSERT INTO user (name, surname, phone_number, employer_id, user_type) VALUES ('Mikko', 'Putkimies', '+372 580 210 100', 1, 'Driver');

INSERT INTO login_authentication (login_Id, password, user_id) VALUES ('123453', '$2a$10$iaX4E2rvTnBZK1ccj9OfhO8JSTWCeM2LjFDwQmIV9rprRm5JmWsle', 1);
INSERT INTO login_authentication (login_Id, password, user_id) VALUES ('254533', '$2a$10$nhOUrL9l9A0Kqr2R6vfsx.tNLVTihFpV/FUKe2N55OdCvsLz94SQW', 2);
