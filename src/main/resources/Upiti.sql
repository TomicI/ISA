insert into rentacar(naziv,opis) values ('Hertz','Rent A Car');
insert into rentacar(naziv,opis) values ('Sixt','Car rental agency in Amsterdam, Netherlands');
insert into rentacar(naziv,opis) values ('Avis','Rent A Car agency');
insert into rentacar(naziv,opis) values ('Europcar','Car rental agency');


insert into filijala(adresa,rentacar_id) values ('Via dell Argilla, 5, 00165 Roma RM, Italy',1);
insert into filijala(adresa,rentacar_id) values ('Roma Fiumicino Aeroporto,Italy',1);
insert into filijala(adresa,rentacar_id) values ('Via Alberto Cadlolo, 101, 00136 Roma RM, Italy',1);

insert into filijala(adresa,rentacar_id) values ('Joop Geesinkweg 125, 1114 AB Amsterdam, Netherlands',2);
insert into filijala(adresa,rentacar_id) values ('De Ruijterkade 44b, 1012 AA Amsterdam, Netherlands',2);

insert into filijala(adresa,rentacar_id) values ('Av. Infante Dom Henrique, 1149-066 Lisboa, Portugal',3);
insert into filijala(adresa,rentacar_id) values ('Aeroporto De Lisboa, 1700-008 Lisboa, Port',3);

insert into filijala(adresa,rentacar_id) values ('Budapest, BUD Nemzetkozi Repuloter, 2B, 1185 Hungary',4);
insert into filijala(adresa,rentacar_id) values ('Budapest, Arany János u. 26-28, 1051 Hungary',4);


insert into vozilo(broj_sedista,broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv,rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,2,1,true,'RENAULT',1,'CLIO','RENAULT CLIO',40,4.5,'',1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, rezervoar,potrosnja,dodatniopis, filijala_id) values
(5,2,5,0,true,'NISSAN',1,'MICRA','NISSAN MICRA',42,4.8,'',1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv,  rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,5,0,true,'OPEL',0,'CORSA','OPEL CORSA 1.4 AUTOMATIC',null,null,'',2);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, rezervoar,potrosnja,dodatniopis,filijala_id) values
(5,2,5,1,true,'VW',1,'GOLF','VOLKSWAGEN GOLF 1.6',null,null,'',2);

insert into cenovnik_rentacar(od_datuma,do_datuma,cena,vozilo_id,servis_id) values ('2019-03-02','2019-04-02',30,1,1);
insert into cenovnik_rentacar(od_datuma,do_datuma,cena,vozilo_id,servis_id) values ('2019-02-01','2019-04-02',45,2,1);
insert into cenovnik_rentacar(od_datuma,do_datuma,cena,vozilo_id,servis_id) values ('2019-01-01','2019-12-31',true,35,2);
insert into cenovnik_rentacar(od_datuma,do_datuma,cena,vozilo_id,servis_id) values ('2019-02-01','2019-04-30',true,45,3);

insert into rezervacija_rentacar(cena,datum_rez,datum_preuz,datum_vracanja, filijala_id , vozilo_id,user_id,status,otkazana) values 
(60,'2019-02-08','2019-02-10 12:00','2019-02-14',1,2,4,0,0);

insert into rezervacija_rentacar(cena,datum_rez,datum_preuz,datum_vracanja, filijala_id , vozilo_id,user_id,status,otkazana) values 
(60,'2019-01-28','2019-02-02 12:00','2019-02-06',2,4,4,0,0);


select * from rentacar;

select * from filijala;

select * from vozilo;

select * from cenovnik_rentacar;

select * from rezervacija_rentacar;


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


