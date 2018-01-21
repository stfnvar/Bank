import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { Banka } from '../shared/Banka';

import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import { Output, EventEmitter } from '@angular/core';

@Injectable()
export class BankaService {

  constructor(private http: Http) { }

  private currentBank = new BehaviorSubject<boolean>(false);
  currentState = this.currentBank.asObservable;

  changeState(specific: boolean){
    this.currentBank.next(specific);
  }

  private getIdOfBank = new BehaviorSubject<number>(0);
  currentId = this.getIdOfBank.asObservable;

  @Output() getCurrentId: EventEmitter<any> = new EventEmitter;

  updateId(id: number){
    this.getIdOfBank.next(id);
    this.getCurrentId.emit(id);
  }

  getBanka(id: number): Promise<Banka> {
    return this.http.get('/api/banka/' + id)
      .toPromise().then(response => response.json() as Banka)
      .catch(this.handleError);
  }

  private handleError(error: any) : Promise<any>{
      console.error("An error occured: ", error);
      return Promise.reject(error.message || error);
  }

}
