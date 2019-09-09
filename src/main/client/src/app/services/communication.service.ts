import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  constructor() { }

  private emitChangeSource = new Subject<any>();

  private reservationChoosen = new Subject<any>();

  changeEmitted$ = this.emitChangeSource.asObservable();

  reservationEmitted$=this.reservationChoosen.asObservable();

  emitChange(obj) {
    console.log("EMITT");
    this.emitChangeSource.next(obj);
  }

  reservationChange(res){
    this.reservationChoosen.next(res);
  }
}
