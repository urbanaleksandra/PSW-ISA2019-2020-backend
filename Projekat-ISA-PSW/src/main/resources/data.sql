INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES ('cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455', 1);
INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log) VALUES ('ccadmin', 'ccadmin', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '58768', 'Doze Djerdja 13', 'Novi Sad', 'Srbija', '06648246', '1');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CCADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');


INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0, 3);


INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1541', 'New York City Health Department clinics offer patients health', 'NYC Health Clinic', 0, 0, 0);
INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1541', 'New York City Health Department clinics offer patients health', 'NYC Health1 Clinic', 0, 0, 0);
INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1223', 'New York City Health Department clinics offer patients health', 'NYC Health2 Clinic', 0, 0, 0);
INSERT INTO clinic (address, description, name, pricelist, profit, rating) VALUES ('New York, NY 1223', 'New York City Health Department clinics offer patients health', 'NYC Health3 Clinic', 0, 0, 0);


INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Beogradska 6', 'Bileca', 'Bosnia and Herzegovina', 'masa@gmail.com', 'Marija', 1497,'Gutic', '0640589536', 'masa', 'masa', '1');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'ana@gmail.com', 'Ana', 5097,'Nikolic', '0640589536', 'anaN', 'ana', '2');

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko', 25097,'Markovic', '0640589536', 'markoM', 'markoM', '3');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Marko2', 1497,'Markovic2', '0640589536', 'marko', 'marko1', '4');
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id) VALUES ('Mise Dimitrijevica 7', 'Novi Sad', 'Serbia', 'marko@gmail.com', 'Ana', 5097,'Markovic', '0640589536', 'anaM', 'anaM', '5');

INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('2', 'nurse', '123', 'nurse@gmail.com', 'Ivana', 'Petrovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', 2, 'nurse',"07:00","14:00");

INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'doctor', '123', 'doctor@gmail.com', 'Djordje', 'Rakic', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', 3, 'doctor', 1,"08:00","15:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'Jovan', '123', 'doctorJovan@gmail.com', 'Jovan', 'Jovic', '2855558', 'Bulevar Cara Dusana 5', 'Novi Sad', 'Serbia', '0655555565', 4, 'doctor', 2,"08:00","15:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'Milan', '123', 'milan@gmail.com', 'Milan', 'Milic', '566987', 'Bulevar Cara Lazara 65', 'Novi Sad', 'Serbia', '0623566665', 0, 'doctor', 3,"09:00","16:00");
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES ('1', 'drPera', '123', 'pera@gmail.com', 'Pera', 'Simic', '1111558', 'Danila Kisa 5', 'Novi Sad', 'Serbia', '0644455565', 4, 'doctor', 1,"07:00","14:00");
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

INSERT INTO surgery (date, description, patient, duration, clinic_id) VALUES ("2020-02-14T09:00", 'operacija nosa', 'anaM', 3, 1);
INSERT INTO surgery (date, description, patient, duration, clinic_id) VALUES ("2020-02-22T12:00", 'operacija kicme', 'markoM', 3, 2);
INSERT INTO surgery (date, description, patient, duration, clinic_id) VALUES ("2020-02-02T12:00", 'operacija jetre', 'masa', 3, 2);
INSERT INTO surgery (date, description, patient, duration, clinic_id, hospital_room_id) VALUES ("2020-02-22T09:00", 'operacija mozga', 'marko1', 3, 1, 1);
INSERT INTO surgery (date, description, patient, duration, clinic_id, hospital_room_id) VALUES ("2020-02-23T09:00", 'operacija prsta', 'ana', 3, 1, 2);

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

INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('2020-01-27T16:30', 'opis1', 1, 'anaM', 'tip1');
INSERT INTO request_appointments (date, description, duration, patient, type) VALUES ('2020-01-23T10:00', 'opis1', 2, 'markoM', 'tip1');

INSERT INTO appointments (date, description, duration, patient,finished, type, doctor_id) VALUES ('2020-01-20T16:30', 'cold', 1, 'anaM', false,'appointment', 2);
INSERT INTO appointments (date, description, duration, patient,finished, type, doctor_id) VALUES ('2020-02-25T11:30', 'broken leg', 3, 'anaM',true, 'surgery', 2);

INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id) VALUES ('2020-01-30T09:15', 'pregled ledja', 1, 'anaM', 'tip pregleda', true, 3);
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id) VALUES ('2020-12-15T08:00', 'opis pregleda', 1, 'markoM', 'tip2', true, 2);
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id) VALUES ('2020-06-13T08:00', 'pregled ruke', 1, 'markoM', 'tip2', true, 3);
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id) VALUES ('2020-01-10T13:00', 'pregled glave', 1, 'anaM', 'tip123', true, 5);
INSERT INTO appointments (date, description, duration, patient, type, finished, doctor_id) VALUES ('2020-02-23T10:00', 'pregled grla', 2, 'markoM', 'tip2', false, 2);

--brzi pregledi(Nemaju dodeljenog pacijenta, ni medical_record)
INSERT INTO appointments (date, description, duration, type, finished, doctor_id, hospital_room_id, doctor_username) VALUES ('2020-08-20T12:00', 'opsti pregled', 2, 'tip7', false, 3, 4, 'Jovan');
INSERT INTO appointments (date, description, duration, type, finished, doctor_id, hospital_room_id, doctor_username) VALUES ('2020-10-15T08:00', 'opsti pregled', 2, 'tip5', false, 2, 5, 'doctor');

INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 1);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 2);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(3, 3);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 4);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 5);

INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (4,8);
INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (5,9);

INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,2);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (2,3);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,5);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (3,4);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,6);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,7);

INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 5, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (1, 3, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (3, 5, 3);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 3, 3);

INSERT INTO patient_rated_clinic(ocena, patient_id, clinic_id) VALUES (3, 5, 1);

INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (1, 1);
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
INSERT INTO appointment_type(name) VALUES ("stomatoloski");


INSERT INTO clinic_clinic_administrator(clinic_id, clinic_administrator_id) VALUES (1,1);


INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,1);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,2);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,3);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,4);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,5);

INSERT INTO price_list(price,appointment_type_id,clinic_id) values (1200,1,1);
INSERT INTO price_list(price,appointment_type_id,clinic_id) values (1000,2,1);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (1,1);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,1);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (2,2);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,2);