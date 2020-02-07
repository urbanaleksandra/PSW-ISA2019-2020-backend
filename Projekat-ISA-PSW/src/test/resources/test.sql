CREATE SEQUENCE clinic_administrator_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE clinical_center_administrator_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE AUTHORITY_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE clinic_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE patient_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_staff_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_record_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE hospital_room_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE surgery_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_record_surgeries_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE appointments_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE appointment_type_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE drug_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE recipe_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE diagnosis_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE price_list_id_seq START WITH 1 INCREMENT BY 1;


INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Koste Todorovica 26, 11129 Beograd', 'Klinika za pulmologiju je zdravstvena ustanova za plućne bolesti u sastavu Kliničkog centra Srbije.', 'Klinika za pulmologiju', 0, 0, 0,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0, 3,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Pasterova 2, Beograd', 'New York City Health Department clinics offer patients health', 'Klinika za očne bolesti', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Koste Todorovića 4, 11000 Beograd', 'Klinika za neurohirurgiju je organizaciona jedinica Kliničkog centra Srbije i nastavna baza Medicinskog fakulteta Univerziteta', 'Klinka za nerohirurgiju', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (id,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (nextval('clinic_id_seq'),'Pasterova 2', 'New York City Health Department clinics offer patients health', 'Klinika za fizikalnu medicinu i rehabilitaciju', 0, 0, 4,44.797860, 20.458775);

INSERT INTO public.clinical_center_administrator (id, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log) VALUES (nextval('clinical_center_administrator_id_seq'),'tacajo', '123', 'tacajovicic@gmail.com', 'Tamara', 'Jovicic', '1401998', 'Bulevar Oslobodjenja 150', 'Novi Sad', 'Serbia', '6551585', 1);
INSERT INTO public.clinic_administrator (id,username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES (nextval('clinic_administrator_id_seq'),'cadmin', 'cadmin', 'zeki.sipovac@gmail.com', 'Zeljana', 'Sipovac', '354168465', 'Glavna 54', 'Nevesinje', 'Bosna i Hercegovina', '0645865455', 1);


INSERT INTO public.appointments (id, version, date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username,type2_id) VALUES (nextval('appointments_id_seq'),0,'2020-02-05T16:00', 'cold', 2, 'anaM', false,'appointment', null, null, null, 'doctor',null);
