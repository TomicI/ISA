import * as moment from "moment";
import _date = moment.unitOfTime._date;

export class Aviokompanija {
  id: number;
  naziv: string;
  adresa: string;
  opis: string;
  prosecnaOcena: number;
  lokacija: Lokacija;
  constructor() { }

}

export class Aerodrom {
  id: number;
  naziv: string;
  lokacija: Lokacija;
  constructor() { }

}

export class Lokacija {
  id: number;
  geoSirina: number;
  geoVisina: number;
  adresa:string;
  grad:string;
  drzava:string;

  constructor() { }



}

export class DodatnaUslugaAviokompanija {
  id: number;
  naziv: string;
  opis: string;
  cena: number;
  aviokompanija: Aviokompanija;
  karta: Karta;
  constructor() { }

}

export class Karta {
  id: number;
  let: Let;
  user: User;
  prosecnaOcena: number;

  constructor() { }

}

export class Prtljag {
  id: number;
  duzina: number;
  sirina: number;
  tezina: number;
  cena: number;
  aviokompanija: Aviokompanija;

  constructor() { }

}

export class Putnik {
  id: number;
  ime: string;
  prezime: string;
  brojPasosa: string;
  user: User;

  constructor() { }

}


export class Sediste {
  id: number;
  kolona: number;
  red: number;
  zauzeto: boolean;
  karta: Karta;
  putnik: Putnik;
  prtljag: Prtljag;
  segment: Segment;
  constructor() { }

}


export class Segment {
  id: number;
  duzina: number;
  sirina: number;
  redniBroj: number;
  konfiguracija: KonfiguracijaLeta;
  kategorija: KategorijaSedista;
  constructor() { }

}

export class KategorijaSedista {
  id: number;
  naziv: string;
  cena: number;
  aviokompanija: Aviokompanija;

  constructor() { }

}

export class KonfiguracijaLeta {
  id: number;
  naziv: string;
  aviokompanija: Aviokompanija;

  constructor() { }

}

export class Let {
   id: number;
   aerodrom: Aerodrom;
   destinacija: Lokacija;
   brojSedista: number;
   vremePolaska: Date;
   vremeDolaska: Date;
   presedanja: string;
   brojPresedanja: number;
   vremePutovanja: string;
   duzinaPutovanja: number
   prosecnaOcena : number;
   konfiguracijaLeta: KonfiguracijaLeta;
   opis: string;
   vrstaLeta: VrstaLeta;


  constructor(id: number, aerodrom: Aerodrom, destinacija: Lokacija, brojSedista: number, vremePolaska: Date, vremeDolaska: Date, presedanja: string, brojPresedanja: number, vremePutovanja: string, duzinaPutovanja: number, prosecnaOcena: number, konfiguracijaLeta: KonfiguracijaLeta, opis: string, vrstaLeta: VrstaLeta) {
    this.id = id;
    this.aerodrom = aerodrom;
    this.destinacija = destinacija;
    this.brojSedista = brojSedista;
    this.vremePolaska = vremePolaska;
    this.vremeDolaska = vremeDolaska;
    this.presedanja = presedanja;
    this.brojPresedanja = brojPresedanja;
    this.vremePutovanja = vremePutovanja;
    this.duzinaPutovanja = duzinaPutovanja;
    this.prosecnaOcena = prosecnaOcena;
    this.konfiguracijaLeta = konfiguracijaLeta;
    this.opis = opis;
    this.vrstaLeta = vrstaLeta;
  }
}

export class RentACar {
  id: number;
  naziv: string;
  opis: string;
  adresa: string;

  constructor(
    id?: number,
    naziv?: string,
    opis?: string,
    adresa?: string) {
    this.id = id;
    this.naziv = naziv;
    this.opis = opis;
    this.adresa = adresa;
  }
}

export class Filijala {
  id: number;
  prosecnaOcena: number;
  rentACarDTO: RentACar;
  lokacijaDTO: Lokacija;

  constructor(
    id: number,
    prosecnaOcena: number,
    rentACarDTO: RentACar,
    lokacijaDTO:Lokacija
  ) {

    this.id = id;
    this.prosecnaOcena = prosecnaOcena;
    this.rentACarDTO = rentACarDTO;
    this.lokacijaDTO = lokacijaDTO;

  }
}

export class Vozilo {
  id: number;
  naziv: string;
  marka: string;
  model: string;
  grupa: string;
  brojSedista: number;
  brojVrata: number;
  brojTorbi: number;
  gorivo: Gorivo;
  menjac: Menjac;
  klima: boolean;
  rezervoar: number;
  potrosnja: number;
  dodatniopis: string;
  prosecnaOcena: number;
  filijalaDTO: Filijala;

  constructor(
    id: number,
    naziv: string,
    marka: string,
    model: string,
    grupa: string,
    brojSedista: number,
    brojVrata: number,
    brojTorbi: number,
    gorivo: Gorivo,
    menjac: Menjac,
    klima: boolean,
    rezervoar: number,
    potrosnja: number,
    dodatniopis: string,
    prosecnaOcena: number,
    filijalaDTO: Filijala
  ) {
    this.id = id;
    this.naziv = naziv;
    this.marka = marka;
    this.model = model;
    this.grupa = grupa;
    this.brojSedista = brojSedista;
    this.brojVrata = brojVrata;
    this.brojTorbi = brojTorbi;
    this.gorivo = gorivo;
    this.menjac = menjac;
    this.klima = klima;
    this.rezervoar = rezervoar;
    this.potrosnja = potrosnja;
    this.dodatniopis = dodatniopis;
    this.prosecnaOcena = prosecnaOcena;
    this.filijalaDTO = filijalaDTO;
  }
}

export class CenovnikRent {
  id: number;
  odDatuma: Date;
  doDatuma: Date;
  cena: number;
  voziloDTO: Vozilo;
  filijalaDTO: Filijala;
  rentACarDTO: RentACar;

  constructor(
    id: number,
    odDatuma: Date,
    doDatuma: Date,
    cena: number,
    voziloDTO: Vozilo,
    rentACarDTO: RentACar
  ) {
    this.id = id;
    this.odDatuma = odDatuma;
    this.doDatuma = doDatuma;
    this.cena = cena;
    this.voziloDTO = voziloDTO;
    this.rentACarDTO = rentACarDTO;
  }
}

export class RezervacijaRent {
  id: number;
  datumRez: Date;
  datumPreuz: Date;
  datumVracanja: Date;
  cena: number;
  popust:number;
  filijalaDTO: Filijala;
  filijalaDropDTO: Filijala;
  status: StatusRes;
  otkazana: boolean;
  naPopustu:boolean;
  voziloDTO: Vozilo;
  userDTO: User;

  constructor(
    id: number,
    datumRez: Date,
    datumPreuz: Date,
    datumVracanja: Date,
    cena: number,
    popust:number,
    filijalaDTO: Filijala,
    filijalaDropDTO: Filijala,
    status: StatusRes,
    otkazana: boolean,
    naPopustu:boolean,
    voziloDTO: Vozilo,
    userDTO: User,
  ) {
    this.id = id;
    this.datumRez = datumRez;
    this.datumPreuz = datumPreuz;
    this.datumVracanja = datumVracanja;
    this.cena = cena;
    this.popust = popust;
    this.filijalaDTO = filijalaDTO;
    this.filijalaDropDTO = filijalaDTO;
    this.status = status;
    this.otkazana = otkazana;
    this.naPopustu=naPopustu;
    this.voziloDTO = voziloDTO;
    this.userDTO = userDTO;


  }

}

export class Rezervacija {
  id: number;
  datumVremeP: Date;
  datumVremeS: Date;
  cena: number;
  rezervacijaRentACarDTO: RezervacijaRent;
  userDTO: User;
  kartaDTO: Karta;

  constructor(
    id: number,
    datumVremeP: Date,
    datumVremeS: Date,
    cena: number,
    rezervacijaRentACarDTO: RezervacijaRent,
    UserDTO: User
  ){
    this.id = id;
    this.datumVremeP = datumVremeP;
    this.datumVremeS = datumVremeS;
    this.cena = cena;
    this.rezervacijaRentACarDTO = rezervacijaRentACarDTO;
    this.userDTO = UserDTO;
  }

}

export class UserLogin {
  username: string;
  password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;

  }

}

export class User {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  phone: string;
  city: string;
  reset: boolean;
  lastPasswordResetDate: Date;
  avioID: number;


  constructor(firstName: string, lastName: string, username: string, email: string, password: string, phone: string, city: string,
              reset: boolean, lastPasswordResetDate: Date, avioID: number) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.city = city;
    this.reset = reset;
    this.lastPasswordResetDate = lastPasswordResetDate;
    this.avioID=avioID;
  }

}

export class Ocena{

  id:number;
  ocena:number;
  ocDate:Date;
  aviokompanijaDTO:Aviokompanija;
  letDTO:Let;
  voziloDTO:Vozilo;
  userDTO:User;
  filijalaDTO:Filijala;
  rezervacijaDTO:Rezervacija;


  constructor(id: number, ocena: number, ocDate: Date, aviokompanijaDTO: Aviokompanija, letDTO: Let, voziloDTO: Vozilo, userDTO: User, filijalaDTO: Filijala, rezervacijaDTO: Rezervacija) {
    this.id = id;
    this.ocena = ocena;
    this.ocDate = ocDate;
    this.aviokompanijaDTO = aviokompanijaDTO;
    this.letDTO = letDTO;
    this.voziloDTO = voziloDTO;
    this.userDTO = userDTO;
    this.filijalaDTO = filijalaDTO;
    this.rezervacijaDTO = rezervacijaDTO;
  }
}

export class NewPass {
  oldPass: string;
  newPass: string;

  constructor(oldPass: string, newPass: string) {
    this.oldPass = oldPass;
    this.newPass = newPass;
  }

}

export class Rate{

  avio:number;
  letoc:number;
  fil:number;
  voz:number;
  hot:number;
  sob:number;


  constructor(avio: number, letoc: number, fil: number, voz: number, hot: number, sob: number) {
    this.avio = avio;
    this.letoc = letoc;
    this.fil = fil;
    this.voz = voz;
    this.hot = hot;
    this.sob = sob;
  }
}

export class Invite{
  id: number;
  userSent: User;
  userReceive: User;
  dateSent: Date;
  reservation: Rezervacija;

  Inivte(){}
}

export enum Menjac {
  AUTO,
  MANU
}

export enum Gorivo {
  BENZIN,
  DIZEL
}

export enum StatusRes {
  Reserved,
  PickUp,
  Canceled,
  DropOff
}

export enum VrstaLeta {
  POVRATNI,
  JEDAN_PRAVAC,
  VISE_DESTINACIJA
}
