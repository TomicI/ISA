
export class Aviokompanija {
    constructor(
        public id?: number,
        public naziv?: string,
        public adresa?: string,
        public opis?: string,
        public prosecnaOcena?: number) { }

}

export class Aerodrom {
    constructor(
        public id?: number,
        public nazivAerodroma?: string,
        public destinacijaID?: number) { }

}

export class Destinacija {
    constructor(
        public id?: number,
        public nazivDestinacije?: string) { }

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
    ) { }
}

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
        public rentACarDTO: RentACar) { }
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
