import { Component, OnInit, Directive, forwardRef, Attribute,
        OnChanges, SimpleChanges, Input } from '@angular/core';

import { NG_VALIDATORS, Validator, 
        Validators, AbstractControl, ValidatorFn } from '@angular/forms';

import { PlatiUsluguService } from '../service/plati-uslugu.service';
import { Transakcija } from '../shared/Transakcija';
import { Md5 } from 'ts-md5/dist/md5'; 

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


  transakcija: Transakcija = {
    pan: "",
    sigurnosniKod: "",
    nazivVlasnikaKartice: "",
    datumVazenja: new Date(),
    iznos: "",
    acquirerOrderId: "",
    acquirerTimestamp: ""
  };

  ngOnInit() {}

  postTransakcija(){

    this.transakcija.datumVazenja.setFullYear(Number(this.godinaIsteka));
    this.transakcija.datumVazenja.setMonth(Number(this.mesecIsteka) - 1);
    
    this.transakcija.acquirerTimestamp = this.generateACQUIRER_TIMESTAMP();
    this.transakcija.acquirerOrderId = this.generateACQUIRER_ORDER_ID(this.transakcija.pan, this.transakcija.sigurnosniKod, this.transakcija.acquirerTimestamp);
    
    this.platiUsluguService.postTransakcija(this.transakcija);
  }

  private generateACQUIRER_TIMESTAMP(): string {
    var timestamp = new Date();
    var stringTimestamp;
    stringTimestamp = timestamp.toLocaleDateString() + timestamp.toLocaleTimeString().substring(0, timestamp.toLocaleTimeString().length - 3) + ":" + timestamp.getMilliseconds();
  
    return stringTimestamp;
  }

  private generateACQUIRER_ORDER_ID(pan: string, sigurnosniKod: string, acquirerTimestamp: string) : string{
      var valueForGenerate = pan + sigurnosniKod;
      valueForGenerate =  Md5.hashStr(valueForGenerate) + acquirerTimestamp;
      return valueForGenerate.replace(" ", "");
  }



  submitted = false;
  onSubmit() { this.submitted = true; }

}
