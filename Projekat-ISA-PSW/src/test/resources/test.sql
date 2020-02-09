CREATE SEQUENCE clinic_administrator_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE clinical_center_administrator_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE AUTHORITY_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE clinic_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE patient_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_staff_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_record_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE hospital_room_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE surgery_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE appointments_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE appointment_type_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE drug_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE recipe_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE diagnosis_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE price_list_id_seq START WITH 1 INCREMENT BY 1;
create sequence request_appointments_id_seq START WITH 1 INCREMENT BY 1;


INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Koste Todorovica 26, 11129 Beograd', 'Klinika za pulmologiju je zdravstvena ustanova za plućne bolesti u sastavu Kliničkog centra Srbije.', 'Klinika za pulmologiju', 0, 0, 0,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0, 3,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Pasterova 2, Beograd', 'New York City Health Department clinics offer patients health', 'Klinika za očne bolesti', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Koste Todorovića 4, 11000 Beograd', 'Klinika za neurohirurgiju je organizaciona jedinica Kliničkog centra Srbije i nastavna baza Medicinskog fakulteta Univerziteta', 'Klinka za nerohirurgiju', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Pasterova 2', 'New York City Health Department clinics offer patients health', 'Klinika za fizikalnu medicinu i rehabilitaciju', 0, 0, 4,44.797860, 20.458775);

INSERT INTO public.clinical_center_administrator (id, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log, enabled) VALUES (nextval('clinical_center_administrator_id_seq'),'tacajo', '123', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '1401998', 'Bulevar Oslobodjenja 150', 'Novi Sad', 'Serbia', '6551585', 1, true);
INSERT INTO public.clinic_administrator (id,username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES (nextval('clinic_administrator_id_seq'),'cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455', 1);

INSERT INTO public.appointment_type(id,name) VALUES (nextval('appointment_type_id_seq'),'Kardioloski');

INSERT INTO public.medical_staff (id, medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena) VALUES (nextval('medical_staff_id_seq'),'2', 'nurse', '123', 'tacajovicic@gmail.com', 'Ivana', 'Petrovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', 2, 'nurse','07:00','14:00');
INSERT INTO public.medical_staff (id,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (nextval('medical_staff_id_seq'),'1', 'doctor', '123', 'tacajovicic@gmail.com', 'Djordje', 'Rakic', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', 1, 'doctor', 1,'08:00','15:00', 1);

INSERT INTO public.hospital_room (name,room_number,clinic_id,version) VALUES ('Glavna',1, 1,0);

INSERT INTO public.medical_staff (id,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (nextval('medical_staff_id_seq'),'1', 'doctorPera', '123', 'tacajovicic@gmail.com', 'Pera', 'Peric', '6566565', 'Bulevar Kralja Petra 12', 'Beograd', 'Serbia', '0612645665', 1, 'doctor', 4,'08:00','15:00', 1);


INSERT INTO public.patient (id, address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES (nextval('patient_id_seq'),'Beogradska 6', 'Bileca', 'Bosnia and Herzegovina', 'tacajovicic@gmail.com', 'Marija', 1497,'Gutic', '0640589536', 'masa', 'masa', null, true);
INSERT INTO public.medical_record (id, blood_type, diopter, height, weight, patient_id) VALUES (nextval('medical_record_id_seq'),'A', '+2.25', '185', '95',1);
UPDATE public.patient SET record_id = 1 where id = 1;
INSERT INTO public.appointments (price,id, version, date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username,type2_id) VALUES (2000,nextval('appointments_id_seq'),0,'2020-02-05T16:00', 'cold', 2, 'masa', false,'appointment', 2, null, 1, 'doctor',1);
INSERT INTO public.request_appointments (price,id,date, description, duration, patient, type,clinic_id) VALUES (2000,nextval('request_appointments_id_seq'),'2020-04-02T10:00', 'opis1', 2, 'markoM', 'tip1',1);


INSERT INTO public.hospital_room (name,room_number,clinic_id,version) VALUES ('Mala',1, 1,0);
INSERT INTO public.request_appointments (price,id,date, description, duration, patient, type,clinic_id) VALUES (2000,nextval('request_appointments_id_seq'),'2020-04-02T10:00', 'opis1', 2, 'markoM', 'tip1',1);
INSERT INTO public.clinic_doctors(clinic_id, doctors_id) VALUES (1,2);
INSERT INTO public.clinic_doctors(clinic_id, doctors_id) VALUES (4,3);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,1);
INSERT INTO public.appointments (id,price,version, date, description, duration, type, finished, doctor_id, hospital_room_id, doctor_username) VALUES (nextval('patient_id_seq'),200,0,'2020-08-20T12:00', 'opsti pregled', 2, 'tip7', false, 2, 1, 'Jovan');
INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (1,2);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 2);
