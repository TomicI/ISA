insert into rentacar(naziv,opis) values ('Hertz','Rent A Car');
insert into rentacar(naziv,opis) values ('Sixt','Car rental agency in Amsterdam, Netherlands');
insert into rentacar(naziv,opis) values ('Avis','Rent A Car agency');
insert into rentacar(naziv,opis) values ('Europcar','Car rental agency');


INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (1, 'Via dell Argilla, 5, 00165 Roma RM, Italy', 'Italy', 'Rome', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (2, 'Roma Fiumicino Aeroporto,Italy', 'Italy', 'Rome', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (3, 'Via Alberto Cadlolo, 101, 00136 Roma RM, Italy', 'Italy', 'Rome', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (4, 'Joop Geesinkweg 125, 1114 AB Amsterdam, Netherlands', 'Netherlands', 'Amsterdam', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (5, 'De Ruijterkade 44b, 1012 AA Amsterdam, Netherlands', 'Netherlands', 'Amsterdam', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (6, 'Av. Infante Dom Henrique, 1149-066 Lisboa, Portugal', 'Portugal', 'Lisboa', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (7, 'Aeroporto De Lisboa, 1700-008 Lisboa, Portugal', 'Portugal', 'Lisboa', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (8, 'Budapest, BUD Nemzetkozi Repuloter, 2B, 1185 Hungary', 'Hungary', 'Budapest', 0, 0);
INSERT INTO projekatISA.lokacija (id, adresa, drzava, grad, geo_sirina, geo_visina) VALUES (9, 'Budapest, Arany János u. 26-28, 1051 Hungary', 'Hungary', 'Budapest', 0, 0);


insert into filijala(rentacar_id,lokacija_id) values (1,1);
insert into filijala(rentacar_id,lokacija_id) values (1,2);
insert into filijala(rentacar_id,lokacija_id) values (1,3);

insert into filijala(rentacar_id,lokacija_id) values (2,4);
insert into filijala(rentacar_id,lokacija_id) values (2,5);

insert into filijala(rentacar_id,lokacija_id) values (3,6);
insert into filijala(rentacar_id,lokacija_id) values (3,7);

insert into filijala(rentacar_id,lokacija_id) values (4,8);
insert into filijala(rentacar_id,lokacija_id) values (4,9);


insert into vozilo(broj_sedista,broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv,rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,2,1,true,'RENAULT',1,'CLIO','RENAULT CLIO',40,4.5,'',1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, rezervoar,potrosnja,dodatniopis, filijala_id) values
(5,2,5,0,true,'NISSAN',1,'MICRA','NISSAN MICRA',42,4.8,'',1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv,  rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,5,0,true,'OPEL',0,'CORSA','OPEL CORSA 1.4 AUTOMATIC',null,null,'',2);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,5,1,true,'VW',1,'GOLF','VOLKSWAGEN GOLF 1.6',null,null,'',2);


INSERT INTO projekatISA.users (id, city, email, enabled, first_name, last_name, last_password_reset_date, password, phone, reset, username, putnik_id, rent_id, broj_pasosa, points) VALUES (1, null, 'mailad@mail.com', true, 'Admin', 'Admin', null, '$2a$10$eukKXJfuGd8.ab3sZgRO9eWfWhwVP3CGTZ3U.WRjb9FXLTgQW7o/W', null, false, 'glavni', null, null, null, 0);
INSERT INTO projekatISA.users (id, city, email, enabled, first_name, last_name, last_password_reset_date, password, phone, reset, username, putnik_id, rent_id, broj_pasosa, points) VALUES (2, null, 'mailadr@mail.com', true, 'Andrew', 'Wisse', '2019-08-30 17:14:12', '$2a$10$qhdp59ZoulmDgaI1.osB0ezlsDOsRw5WMI0KaL/V3Fi906gtut0HW', null, true, 'andrew', null, 1, null, 0);
INSERT INTO projekatISA.users (id, city, email, enabled, first_name, last_name, last_password_reset_date, password, phone, reset, username, putnik_id, rent_id, broj_pasosa, points) VALUES (3, null, 'mailadr@mail.com', true, 'John', 'Green', null, '$2a$10$Rw.FhL3xsLQqytz1YoukAeOv5kS8hFxWff3sJa49Zw8.D1FBDAj3S', null, false, 'john', null, 2, null, 0);
INSERT INTO projekatISA.users (id, city, email, enabled, first_name, last_name, last_password_reset_date, password, phone, reset, username, putnik_id, rent_id, broj_pasosa, points) VALUES (4, null, 'mailru@mail.com', true, 'User', 'User', null, '$2a$10$/zCszDHxHo25nJH0ex0LeOvehOCK.tX1cUUPTPinARX62AOFxGGdi', null, false, 'user', null, null, null, 0);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO user_role (user_id, role_id) VALUES (1, 4);
INSERT INTO user_role (user_id, role_id) VALUES (1, 5);

INSERT INTO user_role (user_id, role_id) VALUES (2, 4);
INSERT INTO user_role (user_id, role_id) VALUES (2, 5);

INSERT INTO user_role (user_id, role_id) VALUES (3, 4);
INSERT INTO user_role (user_id, role_id) VALUES (3, 5);

INSERT INTO user_role (user_id, role_id) VALUES (4, 5);

INSERT INTO projekatISA.friend (id) VALUES (1);
INSERT INTO projekatISA.friend (id) VALUES (2);
INSERT INTO projekatISA.friend (id) VALUES (3);
INSERT INTO projekatISA.friend (id) VALUES (4);

INSERT INTO projekatISA.vozilo (id, broj_sedista, broj_torbi, broj_vrata, dodatniopis, gorivo, klima, marka, menjac, model, naziv, potrosnja, rezervoar, filijala_id) VALUES (1, 5, 2, 2, '', 1, true, 'RENAULT', 1, 'CLIO', 'RENAULT CLIO', 4.5, 40, 1);
INSERT INTO projekatISA.vozilo (id, broj_sedista, broj_torbi, broj_vrata, dodatniopis, gorivo, klima, marka, menjac, model, naziv, potrosnja, rezervoar, filijala_id) VALUES (2, 5, 2, 5, '', 0, true, 'NISSAN', 1, 'MICRA', 'NISSAN MICRA', 4.8, 42, 1);
INSERT INTO projekatISA.vozilo (id, broj_sedista, broj_torbi, broj_vrata, dodatniopis, gorivo, klima, marka, menjac, model, naziv, potrosnja, rezervoar, filijala_id) VALUES (3, 5, 2, 5, '', 0, true, 'OPEL', 0, 'CORSA', 'OPEL CORSA 1.4 AUTOMATIC', null, null, 2);
INSERT INTO projekatISA.vozilo (id, broj_sedista, broj_torbi, broj_vrata, dodatniopis, gorivo, klima, marka, menjac, model, naziv, potrosnja, rezervoar, filijala_id) VALUES (4, 5, 2, 5, '', 1, true, 'VW', 1, 'GOLF', 'VOLKSWAGEN GOLF 1.6', null, null, 2);


INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (1, 42, '2019-08-31 10:00:00', '2019-08-01 10:00:00', 1, 1);
INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (2, 55, '2019-08-31 10:00:00', '2019-08-01 10:00:00', 1, 2);
INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (3, 53, '2019-08-23 22:00:00', '2019-08-14 22:00:00', 1, 4);
INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (4, 41, '2019-08-31 10:00:00', '2019-08-15 10:00:00', 1, 3);
INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (5, 60, '2019-08-31 22:00:00', '2019-08-23 22:00:00', 1, 4);
INSERT INTO projekatISA.cenovnik_rentacar (id, cena, do_datuma, od_datuma, servis_id, vozilo_id) VALUES (6, 70, '2019-09-07 22:00:00', '2019-08-31 22:00:00', 1, 4);
