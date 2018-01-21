export class Transakcija {

    constructor(pan:string, sigurnosniKod: string, nazivVlasnikaKartice: string,
         datumVazenja: Date, iznos: string, acquirerOrderId: string, acquirerTimestamp:string){
            this.pan = pan;
            this.sigurnosniKod = sigurnosniKod;
            this.iznos = iznos;
            this.datumVazenja = datumVazenja;
            this.nazivVlasnikaKartice = nazivVlasnikaKartice;
            this.acquirerOrderId = acquirerOrderId;
            this.acquirerTimestamp = acquirerTimestamp;
    }

    pan: string;
	sigurnosniKod: string;
	nazivVlasnikaKartice: string;
	datumVazenja: Date;
	iznos: string;
	
	acquirerOrderId: string;
	acquirerTimestamp: string;
}