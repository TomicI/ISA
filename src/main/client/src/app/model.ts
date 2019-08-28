export class Aviokompanija {
  constructor(
    public id?: number,
    public naziv?: string,
    public adresa?: string,
    public opis?: string,
    public prosecnaOcena?: number) {
  }

}

export class Aerodrom {
  constructor(
    public id?: number,
    public nazivAerodroma?: string,
    public destinacijaID?: number) {
  }

}

export class Destinacija {
  constructor(
    public id?: number,
    public nazivDestinacije?: string) {
  }

}

export class Let {
  constructor(
    public id?: number,
    public aerodromP?: Aerodrom,
    public aerodromS?: Aerodrom,
    public brojSedista?: number,
    public datumP?: Date,
    public datumS?: Date,
    public vremeP?: String,
    public vremeS?: String,
    public aviokompanijaID?: number,
    public presedanje?: Let,
    public imaPresedanje?: Boolean,
    public vremePutovanja?: String,
    public duzinaPutovanja?: Number
  ) {
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
  adresa: string;
  prosecnaOcena: number;
  rentACarDTO: RentACar;

  constructor(
    id: number,
    adresa: string,
    prosecnaOcena: number,
    rentACarDTO: RentACar) {

    this.id = id;
    this.adresa = adresa;
    this.prosecnaOcena = prosecnaOcena;
    this.rentACarDTO = rentACarDTO;

  }
}

export class Vozilo {
  id: number;
  naziv: string;
  marka: string;
  model: string;
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
  FilijalaDTO: Filijala;
  FilijalaDropDTO: Filijala;
  status: StatusRes;
  otkazana: boolean;
  VoziloDTO: Vozilo;
  UserDTO: User;

  constructor(
    id: number,
    datumRez: Date,
    datumPreuz: Date,
    datumVracanja: Date,
    cena: number,
    FilijalaDTO: Filijala,
    FilijalaDropDTO: Filijala,
    status: StatusRes,
    otkazana: boolean,
    VoziloDTO: Vozilo,
    UserDTO: User,
  ) {
    this.id = id;
    this.datumRez = datumRez;
    this.datumPreuz = datumPreuz;
    this.datumVracanja = datumVracanja;
    this.cena = cena;
    this.FilijalaDTO = FilijalaDTO;
    this.FilijalaDropDTO = FilijalaDTO;
    this.status = status;
    this.otkazana = otkazana;
    this.VoziloDTO = VoziloDTO;
    this.UserDTO = UserDTO;


  }

}

export class Rezervacija {
  id: number;
  datumVremeP: Date;
  datumVremeS: Date;
  cena: number;
  RezervacijaRentACarDTO: RezervacijaRent;
  UserDTO: User;

  constructor(
    id: number,
    datumVremeP: Date,
    datumVremeS: Date,
    cena: number,
    RezervacijaRentACarDTO: RezervacijaRent,
    UserDTO: User
  ){
    this.id = id;
    this.datumVremeP = datumVremeP;
    this.datumVremeS = datumVremeS;
    this.cena = cena;
    this.RezervacijaRentACarDTO = RezervacijaRentACarDTO;
    this.UserDTO = UserDTO;
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


  constructor(firstName: string, lastName: string, username: string, email: string, password: string, phone: string, city: string,
              reset: boolean, lastPasswordResetDate: Date) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.city = city;
    this.reset = reset;
    this.lastPasswordResetDate = lastPasswordResetDate;
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
