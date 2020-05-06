import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenushareService {
  private menu = new Subject<any>();
  private lang = new Subject<any>();
  private header = new Subject<any>();
  private user = new Subject<any>();
  private app = new Subject<any>();
  private notif = new Subject<any>();

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


getuser(): Observable<any> {
  return this.user.asObservable();
}

updateuser(message: any) {
this.user.next(message);
}


getapp(): Observable<any> {
  return this.app.asObservable();
}

updateapp(message: any) {
this.app.next(message);
}



getnotif(): Observable<any> {
  return this.notif.asObservable();
}

updatenotif(message: any) {
this.notif.next(message);
}

}
