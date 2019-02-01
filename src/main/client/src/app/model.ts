export class RentACar {
    id: number;
    naziv: string;
    opis: string;

    constructor(
        id?: number,
        naziv?: string,
        opis?: string) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
    }
}

export class Filijala {
    constructor(
        public id: number,
        public adresa: string,
        public prosecnaOcena: string,
        public rentACarDTO = RentACar) { }
}

export class Vozilo {
    constructor(

        public id: number,
        public naziv: string,
        public marka: string,
        public model: string,
        public brojSedista: number,
        public brojVrata: number,
        public brojTorbi: number,
        public gorivo: Gorivo,
        public menjac: Menjac,
        public klima: boolean,
        public rezervoar: number,
        public potrosnja: number,
        public dodatniopis: string,
        public prosecnaOcena: number,
        public filijalaDTO: Filijala

    ) { }
}

export class CenovnikRent {
    constructor(
        public id: number,
        public odDatuma: Date,
        public doDatuma: Date,
        public cena: number,
        public slobodan: boolean,
        public voziloDTO: Vozilo
    ) { }
}

export class RezervacijaRent {
    constructor(
        public id: number,
        public datumRez: Date,
        public datumPreuz: Date,
        public datumVracanja: Date,
        public cena: number,
        public FilijalaDTO: Filijala,
        public VoziloDTO: Vozilo,
    ) { }

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

    constructor(firstName: string, lastName: string, username: string, email: string, password: string) { };

}


export enum Menjac {
    AUTO,
    MANU
}

export enum Gorivo {
    BENZIN,
    DIZEL
}
