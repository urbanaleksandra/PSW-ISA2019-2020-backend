INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number) VALUES ('cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455');
INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log) VALUES ('ccadmin', 'ccadmin', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '58768', 'Doze Djerdja 13', 'Novi Sad', 'Srbija', '06648246', '1');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CCADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');


INSERT INTO clinic (address, description, name, pricelist, profit) VALUES ('New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0);

INSERT INTO clinic (address, description, name, pricelist, profit) VALUES ('New York, NY 1541', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Beogradska 6', 'Bileca', 'Bosnia and Herzegovina', 'masa@gmail.com', 'Marija', 1497,'Gutic', '0640589536', 'masa', 'masa', '1');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'ana@gmail.com', 'Ana', 5097,'Nikolic', '0640589536', 'anaN', 'ana', '2');

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko', 25097,'Markovic', '0640589536', 'markoM', 'markoM', '3');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko2', 1497,'Markovic2', '0640589536', 'marko', 'marko1', '4');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Ana', 5097,'Markovic', '0640589536', 'anaM', 'anaM', '5');
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role) VALUES ('2', 'nurse', '123', 'nurse@gmail.com', 'Ivana', 'Petrovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', '0', 'nurse');
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role) VALUES ('1', 'doctor', '123', 'doctor@gmail.com', 'Djordje', 'Rakic', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', '0', 'doctor');

INSERT INTO medical_record (patient_id) VALUES (1);
INSERT INTO medical_record (patient_id) VALUES (2);
INSERT INTO medical_record (patient_id) VALUES (3);
INSERT INTO medical_record (patient_id) VALUES (4);
INSERT INTO medical_record (patient_id) VALUES (5);

INSERT INTO hospital_room (name,room_number) VALUES ('GLAVNA SALA',1);
INSERT INTO hospital_room (name,room_number) VALUES ('Operaciona',2);
INSERT INTO hospital_room (name,room_number) VALUES ('Operaciona sala',3);

INSERT INTO surgery (date, description, patient) VALUES ("2019-12-20", 'operacija nosa', 'anaM');
INSERT INTO surgery (date, description, patient) VALUES ("2020-3-24", 'operacija kicme', 'markoM');


INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('10.12.2019', 'opis1', 1, 'anaM', 'tip1');
INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('10.12.2019', 'opis1', 1, 'markoM', 'tip1');

INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUE (1, 1);
INSERT INTO drug(name, price, quantity) VALUES ("brufen", 259, 100);
INSERT INTO drug(name, price, quantity) VALUES ("fervex", 550, 200);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "dva puta po dve tablete", 1);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "tri kesice na dan", 2);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "jedna kesica na dan", 2);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (true, "dve kesice na dan", 2);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUE (1,1);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUE (2,2);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUE (2,3);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUE (2,4);