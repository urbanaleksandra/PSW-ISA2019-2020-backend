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
CREATE SEQUENCE drug_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE diagnosis_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE price_list_id_seq START WITH 1 INCREMENT BY 1;


INSERT INTO public.clinic (address, description, name, pricelist, profit, rating,longitude,lat) VALUES ('Koste Todorovica 26, 11129 Beograd', 'Klinika za pulmologiju je zdravstvena ustanova za plućne bolesti u sastavu Kliničkog centra Srbije.', 'Klinika za pulmologiju', 0, 0, 0,44.797860, 20.458775);
INSERT INTO public.clinic (address, description, name, pricelist, profit, rating,longitude,lat) VALUES ('New York, NY 10011', '7 days a week | Your health is our priority', 'NYC Free Clinic', 0, 0, 3,44.797860, 20.458775);
INSERT INTO public.clinic (address, description, name, pricelist, profit, rating,longitude,lat) VALUES ('Pasterova 2, Beograd', 'New York City Health Department clinics offer patients health', 'Klinika za očne bolesti', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (address, description, name, pricelist, profit, rating,longitude,lat) VALUES (' Koste Todorovića 4, 11000 Beograd', 'Klinika za neurohirurgiju je organizaciona jedinica Kliničkog centra Srbije i nastavna baza Medicinskog fakulteta Univerziteta', 'Klinka za nerohirurgiju', 0, 0, 4,44.797860, 20.458775);
INSERT INTO public.clinic (address, description, name, pricelist, profit, rating,longitude,lat) VALUES ('Pasterova 2', 'New York City Health Department clinics offer patients health', 'Klinika za fizikalnu medicinu i rehabilitaciju', 0, 0, 4,44.797860, 20.458775);
