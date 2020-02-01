INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES ('cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455', 1);
INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log) VALUES ('ccadmin', 'ccadmin', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '58768', 'Doze Djerdja 13', 'Novi Sad', 'Srbija', '06648246', '1');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CCADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');


INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0, 3);

INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1541', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0, 4);
INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1223', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0, 4);
INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1223', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0, 4);


INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Beogradska 6', 'Bileca', 'Bosnia and Herzegovina', 'tacajovicic@gmail.com', 'Marija', 1497,'Gutic', '0640589536', 'masa', 'masa', '1');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'tacajovicic@gmail.com', 'Ana', 5097,'Nikolic', '0640589536', 'anaN', 'ana', '2');

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'tacajovicic@gmail.com', 'Marko', 25097,'Markovic', '0640589536', 'markoM', 'markoM', '3');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'tacajovicic@gmail.com', 'Marko2', 1497,'Markovic2', '0640589536', 'marko', 'marko1', '4');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'tacajovicic@gmail.com', 'Ana', 5097,'Markovic', '0640589536', 'anaM', 'anaM', '5');

INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('2', 'nurse', '123', 'nurse@gmail.com', 'Ivana', 'Petrovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', 2, 'nurse',"07:00","14:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'doctor', '123', 'tacajovicic@gmail.com', 'Djordje', 'Rakic', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', 1, 'doctor', 1,"08:00","15:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'Jovan', '123', 'tacajovicic@gmail.com', 'Jovan', 'Jovic', '2855558', 'Bulevar Cara Dusana 5', 'Novi Sad', 'Serbia', '0655555565', 3, 'doctor', 2,"08:00","15:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'Milan', '123', 'tacajovicic@gmail.com', 'Milan', 'Milic', '566987', 'Bulevar Cara Lazara 65', 'Novi Sad', 'Serbia', '0623566665', 0, 'doctor', 3,"09:00","16:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'drPera', '123', 'tacajovicic@gmail.com', 'Pera', 'Simic', '1111558', 'Danila Kisa 5', 'Novi Sad', 'Serbia', '0644455565', 4, 'doctor', 1,"07:00","14:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'drMr', '123', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '6546546', 'Glavna 123', 'Novi Sad', 'Serbia', '065684652', 4, 'doctor', 1,"07:00","14:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'drMr', '123', 'tacajovicic@gmail.com', 'Mila', 'Katic', '6595026', 'Bulevar Oslobodjenja 13', 'Novi Sad', 'Serbia', '063585526', 4, 'doctor', 1,"07:00","14:00");

INSERT INTO medical_record (patient_id) VALUES (1);
INSERT INTO medical_record (patient_id) VALUES (2);
INSERT INTO medical_record (patient_id) VALUES (3);
INSERT INTO medical_record (patient_id) VALUES (4);
INSERT INTO medical_record (patient_id) VALUES (5);


INSERT INTO hospital_room (name,room_number,clinic_id) VALUES ('Glavna sala',1, 1);
INSERT INTO hospital_room (name,room_number,clinic_id) VALUES ('Operaciona sala',2, 1);
INSERT INTO hospital_room (name,room_number,clinic_id) VALUES ('Operaciona sala',3, 1);
INSERT INTO hospital_room (name,room_number,clinic_id) VALUES ('Mala sala',23, 2);
INSERT INTO hospital_room (name,room_number,clinic_id) VALUES ('Velika sala',24, 2);

INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-22T16:00", 'operacija nosa', 'anaM', 2, 1, 5);
INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-22T16:00", 'operacija kicme', 'markoM', 2, 2, 3);
INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-22T16:00", 'operacija jetre', 'masa', 2, 2, 1);
INSERT INTO surgery (date, description, patient, duration, clinic_id, hospital_room_id, medical_record_id) VALUES ("2020-02-22T14:00", 'operacija mozga', 'marko1', 2, 1, 1, 4);
INSERT INTO surgery (date, description, patient, duration, clinic_id, hospital_room_id, medical_record_id) VALUES ("2020-02-23T14:00", 'operacija prsta', 'ana', 2, 1, 2, 2);
INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-22T16:00", 'operacija hitna', 'marko1', 2, 1, 4);
INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-14T14:00", 'noga', 'masa', 2, 1, 1);
INSERT INTO surgery (date, description, patient, duration, clinic_id, medical_record_id) VALUES ("2020-02-14T14:00", 'ruka', 'ana', 2, 1, 2);

INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (5, 1);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (3, 2);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (1, 3);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (4, 4);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (2, 5);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (4, 6);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (1, 7);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (2, 8);

INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (4,2);
INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (4,5);
INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (5,5);


INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (2,4);
INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (5,4);
INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (5,5);

INSERT INTO hospital_room_surgeries(hospital_room_id, surgeries_id) VALUES (1,4);
INSERT INTO hospital_room_surgeries(hospital_room_id, surgeries_id) VALUES (2,5);


INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,1);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (2,2);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (2,3);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,4);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,5);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,6);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,7);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,8);

INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('2020-01-27T16:00', 'opis1', 2, 'anaM', 'tip1');
INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('2020-01-23T10:00', 'opis1', 2, 'markoM', 'tip1');

INSERT INTO appointments (date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username) VALUES ('2020-02-01T16:00', 'cold', 2, 'anaM', false,'appointment', 2, 1, 5, 'doctor');
INSERT INTO appointments (date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username) VALUES ('2020-02-01T16:00', 'broken leg', 2, 'anaM',true, 'surgery', 2, 2, 5, 'doctor');
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username) VALUES ('2020-01-30T10:00', 'pregled ledja', 2, 'anaM', 'tip pregleda', false, 3, 5, 'Jovan');
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username) VALUES ('2020-12-15T16:00', 'opis pregleda', 2, 'markoM', 'tip2', false, 2, 3, 'doctor');
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username) VALUES ('2020-02-23T16:00', 'pregled grla', 2, 'markoM', 'tip2', false, 2, 3, 'doctor');

INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,1);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,2);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,3);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,4);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,5);

INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (1,1);
INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (2,2);

INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 1);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 2);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(3, 3);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 4);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 5);

INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,2);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (2,3);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,5);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (3,4);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,6);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,7);


INSERT INTO drug(name, price, quantity) VALUES ("brufen", 259, 100);
INSERT INTO drug(name, price, quantity) VALUES ("fervex", 550, 200);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "dva puta po dve tablete", 1);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "tri kesice na dan", 2);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (false, "jedna kesica na dan", 2);
INSERT INTO recipe(authenticated, description, drug_id) VALUES (true, "dve kesice na dan", 2);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUES (1,1);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUES (2,2);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUES (2,3);
INSERT INTO drug_recipe(drug_id, recipe_id) VALUES (2,4);

INSERT INTO appointment_type(name) VALUES ("kardioloski");


INSERT INTO clinic_clinic_administrator(clinic_id, clinic_administrator_id) VALUES (1,1);


INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,1);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,2);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,3);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,4);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,5);

