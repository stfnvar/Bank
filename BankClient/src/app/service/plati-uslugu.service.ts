import { Injectable } from '@angular/core';

import { Http } from '@angular/http'

import { Transakcija } from '../shared/Transakcija';

@Injectable()
export class PlatiUsluguService {

  constructor(private http: Http) {}

  postTransakcija(transakcija: Transakcija){
    
      this.http.post('/api/placanje/unesiPodatke/', transakcija).toPromise();
  }

}
