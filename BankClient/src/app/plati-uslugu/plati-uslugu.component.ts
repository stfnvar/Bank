import { Component, OnInit, Directive, forwardRef, Attribute,
        OnChanges, SimpleChanges, Input } from '@angular/core';

import { NG_VALIDATORS, Validator, 
        Validators, AbstractControl, ValidatorFn } from '@angular/forms';

import {Router, ActivatedRoute, Params} from '@angular/router';

import { PlatiUsluguService } from '../service/plati-uslugu.service';
import { Transakcija } from '../shared/Transakcija';
import { Md5 } from 'ts-md5/dist/md5'; 

import { Uplata } from '../shared/Uplata';


@Component({
  selector: 'app-plati-uslugu',
  templateUrl: './plati-uslugu.component.html',
  styleUrls: ['./plati-uslugu.component.css']
})
export class PlatiUsluguComponent implements OnInit {

  constructor(private platiUsluguService: PlatiUsluguService, private activatedRoute: ActivatedRoute, private router: Router) { }

  vlasnik: string;
  mesecIsteka: string;
  godinaIsteka: string;
  sigurnosniBroj: string;
  brojKartice: string;

  uplata: Uplata;

  uspesnaTransakcija: string;

  transakcija: Transakcija = {
    pan: "",
    sigurnosniKod: "",
    nazivVlasnikaKartice: "",
    datumVazenja: new Date(),
    iznos: "",
    acquirerOrderId: "",
    acquirerTimestamp: "",
    uplataId: 0
  };

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      let paymentUrl = params["payment_url"];
      let paymentId = params["payment_id"]; 
      this.platiUsluguService.checkPaymentValidity(paymentUrl, paymentId)
        .then(uplata =>  {this.uplata = uplata} );
      //if(this.iznos == -1){
        //this.router.navigate(['pr']);
      //}
    });
  }

  postTransakcija(){

    this.transakcija.datumVazenja.setFullYear(Number(this.godinaIsteka));
    this.transakcija.datumVazenja.setMonth(Number(this.mesecIsteka) - 1);
    
    this.transakcija.acquirerTimestamp = this.generateACQUIRER_TIMESTAMP();
    this.transakcija.acquirerOrderId = this.generateACQUIRER_ORDER_ID(this.transakcija.pan, this.transakcija.sigurnosniKod, this.transakcija.acquirerTimestamp);
    
    this.transakcija.iznos = this.uplata.iznos.toString();
    this.transakcija.uplataId = this.uplata.uplataIdDatabase;

    this.platiUsluguService.postTransakcija(this.transakcija).then( () => {
      this.platiUsluguService.checkTransactionSuccess(this.transakcija.uplataId)
      .then(response => 
        {
          if(response)
            this.uspesnaTransakcija = "Uspesna";
          else
            this.uspesnaTransakcija = "Neuspesna";
        });
    });

    
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
