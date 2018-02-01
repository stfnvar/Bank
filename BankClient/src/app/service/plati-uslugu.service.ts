import { Injectable } from '@angular/core';

import { Http } from '@angular/http'

import { Transakcija } from '../shared/Transakcija';
import { error } from 'util';
import { Uplata } from '../shared/Uplata';

@Injectable()
export class PlatiUsluguService {

  constructor(private http: Http) {}

  postTransakcija(transakcija: Transakcija){
     return this.http.post('/api/placanje/unesiPodatke/', transakcija).toPromise();
  }
  
  checkPaymentValidity(paymentUrl: string, paymentId: string): Promise<Uplata> {
    return this.http.get("/api/placanje/proveriUrl?paymentUrl="+paymentUrl + "&paymentId="+paymentId).
    toPromise().then(response => { console.log(response.json()); return response.json() as Uplata;}).catch(this.handleProveraLinkaError);
  }

  checkTransactionSuccess(id: number) : Promise<boolean>{
    return this.http.get("/api/placanje/test?uplataId="+id).
    toPromise().then(response => {console.log(response); return response; }).catch(this.handleError);
  }
    
  private handleError(error: any) : Promise<any>{
      console.error("An error occured: ", error);
      return Promise.reject(error.message || error);
  }

  private handleProveraLinkaError(error: any) : Promise<any>{
    return Promise.apply(window.location.href = "https://localhost:2100/nevalidan-link");
  }
}
