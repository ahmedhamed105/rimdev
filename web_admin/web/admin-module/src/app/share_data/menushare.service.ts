import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenushareService {
  private menu = new Subject<any>();
  private lang = new Subject<any>();
  private header = new Subject<any>();
  constructor() { }


  getlang(): Observable<any> {
    return this.lang.asObservable();
 }

 updatelang(menuin: any) {
  this.lang.next(menuin);
}

  getmenu(): Observable<any> {
    return this.menu.asObservable();
 }

 updatemenu(menuin: any) {
  this.menu.next(menuin);
}


getheader(): Observable<any> {
  return this.header.asObservable();
}

updateheader(message: any) {
this.header.next(message);
}
}
