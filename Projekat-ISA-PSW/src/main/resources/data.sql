INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number) VALUES ('cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455');
INSERT INTO clinical_center_administrator (username, password) VALUES ('ccadmin', 'ccadmin');

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

INSERT INTO appointments (date, description, duration, type, doctor_id, hospital_room_id, medical_record_id, recipe_id) VALUES ('10.12.2019', 'opis1', 1, 'tip1', 2, 1, 1, 1);