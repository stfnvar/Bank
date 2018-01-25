import { Injectable } from '@angular/core';

import { Http } from '@angular/http'

import { Transakcija } from '../shared/Transakcija';
import { error } from 'util';
import { Uplata } from '../shared/Uplata';

@Injectable()
export class PlatiUsluguService {

  constructor(private http: Http) {}

  postTransakcija(transakcija: Transakcija){
      this.http.post('/api/placanje/unesiPodatke/', transakcija).toPromise();
  }
  
  checkPaymentValidity(paymentUrl: string, paymentId: string): Promise<Uplata> {
    return this.http.get("/api/placanje/proveriUrl?paymentUrl="+paymentUrl + "&paymentId="+paymentId).
    toPromise().then(response => { console.log(response.json()); return response.json() as Uplata;}).catch(this.handleError);
  }
    
  private handleError(error: any) : Promise<any>{
      console.error("An error occured: ", error);
      return Promise.reject(error.message || error);
  }
}
