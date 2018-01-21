import { Component, OnInit } from '@angular/core';
import { PlatiUsluguService } from '../service/plati-uslugu.service';
import { Transakcija } from '../shared/Transakcija';

@Component({
  selector: 'app-plati-uslugu',
  templateUrl: './plati-uslugu.component.html',
  styleUrls: ['./plati-uslugu.component.css']
})
export class PlatiUsluguComponent implements OnInit {

  constructor(private platiUsluguService: PlatiUsluguService) { }

  vlasnik: string;
  mesecIsteka: string;
  godinaIsteka: string;
  sigurnosniBroj: string;
  brojKartice: string;

  transakcija: Transakcija;

  ngOnInit() {}

  postTransakcija(){
    
    this.transakcija = {
      pan: this.brojKartice,
      sigurnosniKod: this.sigurnosniBroj,
      nazivVlasnikaKartice: this.vlasnik,
      datumVazenja: new Date(),
      iznos: "0",
      acquirerOrderId: "1",
      acquirerTimestamp: new Date().toDateString()
    };

    this.transakcija.datumVazenja.setMonth(Number(this.mesecIsteka) - 1);
    this.transakcija.datumVazenja.setFullYear(Number(this.godinaIsteka));

    this.platiUsluguService.postTransakcija(this.transakcija);
  }
}
