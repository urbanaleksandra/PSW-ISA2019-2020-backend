INSERT INTO clinic_administrator (username, password) VALUES ('cadmin', 'cadmin');
INSERT INTO clinical_center_administrator (username, password) VALUES ('ccadmin', 'ccadmin');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CCADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');


INSERT INTO clinic (address, description, name, pricelist, profit) VALUES ('New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0);

INSERT INTO clinic (address, description, name, pricelist, profit) VALUES ('New York, NY 1541', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0);

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko', 25097,'Markovic', '0640589536', 'markoM', 'markoM');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko2', 1497,'Markovic2', '0640589536', 'marko', 'marko1');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Ana', 5097,'Markovic', '0640589536', 'anaM', 'anaM');