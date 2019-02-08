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

export class Sediste {
    constructor(
        public id?: number,
        public red?: number,
        public kolona?: number,
        public segment?: number,
        public zauzeto?: Boolean,
        public user?: User) { }

}

export class Karta{
    
    let: Let;
    constructor(
        public id?: number,
        public sediste?: Sediste,
        public prtljag?: Prtljag[],
        public user?: User,
        public dodatneUsluge?: DodatneUslugeAvion[],
        public cena?: CenovnikAvio,
        public prosecnaOcena?: number){
            this.let;
        }
}

export class Prtljag{
    constructor(
        public id?: number,
        public duzina?: number,
        public sirina?: number,
        public tezina?: number,
        public cena?: number
    ){}
}

export class DodatneUslugeAvion{
    constructor(
        public id: number,
        public cena: number,
        public opis: String
    ){}
}

export class CenovnikAvio{
    constructor(
        public id: number,
        public odDatuma: Date,
        public doDatuma: Date,
        public cena: number,
        public popust: number
    ){}
}

export class Let{
    constructor(
        public id?: number,
        public aerodromP?: Aerodrom,
        public aerodromS?: Aerodrom,
        public datumP?: Date,
        public datumS?: Date,
        public vremeP?: String,
        public vremeS?: String,
        public aviokompanijaID?: Aviokompanija,
        public vremePutovanja?: String,
        public duzinaPutovanja?: Number,
        public presedanje?: Aerodrom[],
        public brojSegmenata?: number,
        public brojKolona?: number, 
        public brojRedova?: number,
        public opis?: String,
        public sedista?: Sediste[]
    ){}
}

export class NewPass {
    oldPass: string;
    newPass: string;

    constructor(oldPass: string,newPass: string){
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

}




