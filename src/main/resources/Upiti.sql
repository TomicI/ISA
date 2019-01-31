insert into rentacar(naziv,opis) values ('Novi Sad Rent','Fast Rent A Car');
insert into rentacar(naziv,opis) values ('Mile Rent','Gradski Rent A Car');
insert into rentacar(naziv,opis) values ('X Rent','Pick your Car');

insert into filijala(adresa,prosecna_ocena,rentacar_id) values ('Cara Dusana 72',4,1);
insert into filijala(adresa,prosecna_ocena,rentacar_id) values ('Bulevar Cara Lazara 82',3,1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, prosecna_ocena, filijala_id) values
(5,3,5,0,true,'RENAULT',1,'CLIO','RENAULT CLIO',8,1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, prosecna_ocena, filijala_id) values
(5,2,5,0,true,'NISSAN',1,'MICRA','NISSAN MICRA',7,1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, prosecna_ocena, filijala_id) values
(5,2,5,0,true,'OPEL',0,'CORSA','OPEL CORSA 1.4 AUTOMATIC',8,1);

insert into vozilo(broj_sedista, broj_torbi, broj_vrata,gorivo ,klima, marka, menjac, model, naziv, prosecna_ocena, filijala_id) values
(5,2,5,1,true,'VW',1,'GOLF','VOLKSWAGEN GOLF 1.6',7,1);


insert into cenovnik_rentacar(od_datuma,do_datuma,slobodan,cena,vozilo_id) values ('2018-12-17','2018-12-31',true,30,1);
insert into cenovnik_rentacar(od_datuma,do_datuma,slobodan,cena,vozilo_id) values ('2019-01-01','2019-12-31',true,35,2);
insert into cenovnik_rentacar(od_datuma,do_datuma,slobodan,cena,vozilo_id) values ('2019-02-01','2019-04-30',true,45,3);

insert into rezervacija_rentacar(cena, datum_rez,datum_preuz,datum_vracanja, filijala_id , vozilo_id) values 
(60,'2018-12-17','2018-12-18','2018-12-20',1,1);


select * from rentacar







