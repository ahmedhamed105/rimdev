import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { IdeviceOS } from '../objects/IdeviceOS';
import { Ilogintype } from '../objects/Ilogintype';
import { Idevicetype } from '../objects/Idevicetype';
import { Router } from '@angular/router';
import { devicetoken } from '../objects/devicetoken';
import { GlobalConstants } from '../GlobalConstants';
import { CookiesService } from '../services/cookies.service';



@Injectable({
  providedIn: 'root'
})
export class LocationServiceService {

  public device =  <IDevice>{};

  constructor(private cookieService: CookiesService,private deviceService: DeviceDetectorService,private _http:HttpClient) { }

  



  public deviceos = [];
  public devicetype = [];
  public logintype = [];
  public mydevice;

   async all_info(page ,usertokean,username) 
  {
      const deviceInfo = this.deviceService.getDeviceInfo();
      const isMobilea = this.deviceService.isMobile();
      const isTableta = this.deviceService.isTablet();
      const isDesktopDevicea = this.deviceService.isDesktop();
      const url = location.host;
      const browser = deviceInfo.browser;
      const browser_version=deviceInfo.browser_version;
      const devicename=deviceInfo.device;
      const os=deviceInfo.os;
      const os_version=deviceInfo.os_version;
      const userAgent=deviceInfo.userAgent;


if(os === undefined){
  this.device.deviceOSID={
    id: 0,
    deviceOS: 'unknown'
  };
}else{
  console.log(os)
  this.device.deviceOSID={
    id: 0,
    deviceOS: os
  };
}

    //  console.log(this.deviceService.getDeviceInfo());
    //   await   this.getos(usertokean,username).then(res => {
        
    //     this.deviceos=res;
    // //     console.log('finish2');
    // //     console.log(this.deviceos);
    //     for(var i = 0; i < this.deviceos.length; i++) {
    //    //   console.log(this.deviceos[i].deviceOS);
    //         if (this.deviceos[i].deviceOS == os) {
    //           this.device.deviceOSID=this.deviceos[i];
    //             break;
    //         }
    //     }  
  
    //   //  console.log('finish4');
 
 
    //   });
    if(devicename === undefined){  
    this.device.devicetypeID=
    {
      id: 0,
      devtype: 'unknown'
    };
    }else{
     console.log(devicename)
    this.device.devicetypeID=
    {
      id: 0,
      devtype: 'unknown'
    };
  }

    //  await this.gettype(usertokean,username).then(data => {

    //     this.devicetype = data;

    //   for(var i = 0; i < this.devicetype.length; i++) {
    //   //  console.log(this.devicetype[i].devtype);
    //       if (this.devicetype[i].devtype == devicename) {
    //         this.device.devicetypeID=this.devicetype[i];
    //           break;
    //       }
    //   }
    // });

    

    this.device.devicebrowser=browser;
    this.device.deviceBVersion=browser_version;
    this.device.deviceinfo=browser+browser_version;
    this.device.deviceosversion=os_version;
    this.device.deviceosunknow=userAgent;

    this.device.mobile=isMobilea;
    this.device.tablet=isTableta;
    this.device.desktopDevice=isDesktopDevicea;
    this.device.page=page;


    this.device.logintypeID ={
      id: 0,
      ltype: 'Admin'
    };


    //   await this.getlogintype(usertokean,username).then(data => {

    //     this.logintype = data;

    //   for(var i = 0; i < this.logintype.length; i++) {
    //   //  console.log(this.devicetype[i].devtype);

    //   if (this.logintype[i].ltype == 'unknown') {
    //     this.device.logintypeID=this.logintype[i];
    //   }
    //       if (this.logintype[i].ltype == 'Admin') {
    //         this.device.logintypeID=this.logintype[i];
    //           break;
    //       }
    //   }

    

    // });
 //  await   this.getip().then(data => {
 //      this.device.deviceip=data['ip'];
 //     }).catch((error) => {
//        this.device.deviceip = ''
 //      });

 this.device.deviceip = ''

   // console.log("IP "+this.device.deviceip)
    GlobalConstants.PCCODE= this.cookieService.getCookie('pccode');
        if(GlobalConstants.PCCODE.length === 0){
          GlobalConstants.PCCODE= Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
          this.cookieService.setCookie( 'pccode', GlobalConstants.PCCODE,900,'' ); // To Set Cookie

        }

  this.device.devicecode = GlobalConstants.PCCODE;
  
    await this.getnavigation()
    .then(position => {
      this.device.devicelong=position.coords.longitude;
      this.device.devicelatitude= position.coords.latitude;
    }).catch((error) => {
      this.device.devicelong=0;
      this.device.devicelatitude= 0;
     });
   

  //     console.log('navg '+this.device.devicelong);


      await this.insert(usertokean,username).then(res => {
     this.mydevice =res;
        if(res['devicestatusID']['id'] === 2){
          window.location.replace('/blocked');
        }
      });
   


  }



async  insert(usertokean,username) : Promise<any>{
  
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.Deviceinsert+"/"+GlobalConstants.language;
  let headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
    'username':   username,
    'usertokean': usertokean });
let options = { headers: headers };
  return await   this._http.post<any>(urlall,this.device,options).toPromise();
  
}


async  getos(usertokean,username) : Promise<IdeviceOS[]>{


  // console.log('finish3');
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.Deviceos+"/"+GlobalConstants.language;
  let headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
    'username':   username,
    'usertokean': usertokean });
let options = { headers: headers };
  return await   this._http.get<IdeviceOS[]>(urlall,options).toPromise();
  
}

async  gettype(usertokean,username) : Promise<Idevicetype[]>{

 // console.log('finish5');
 var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.Devicetype+"/"+GlobalConstants.language;
 let headers = new HttpHeaders({
  'Cache-Control': 'no-cache',
  'Pragma': 'no-cache',
  'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
  'username':   username,
  'usertokean': usertokean });
let options = { headers: headers };
  return await   this._http.get<Idevicetype[]>(urlall,options).toPromise();
  
}


async  getlogintype(usertokean,username) : Promise<Ilogintype[]>{
  // console.log('finish5');
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.logintype+"/"+GlobalConstants.language;
  let headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
   'username':   username,
   'usertokean': usertokean });
 let options = { headers: headers };
   return await   this._http.get<Ilogintype[]>(urlall,options).toPromise();
   
 }


async  getip() : Promise<string>{

//  console.log('finish ip');
  return await   this._http.get<string>('https://jsonip.com').toPromise();
  
}


async  getnavigation():Promise<any>{

  return await new Promise(function (resolve, reject) {
    navigator.geolocation.getCurrentPosition(resolve, ({code, message}) =>
    reject(Object.assign(new Error(message), {name: "PositionError", code})), {
      enableHighAccuracy: true,
      timeout: 5000,
      maximumAge: 0
  });
  });
  
}



getQueryParams( params, url ) {
  
  let href = url;
  //this expression is to get the query strings
  let reg = new RegExp( '[?&]' + params + '=([^&#]*)', 'i' );
  let queryString = reg.exec(href);
  return queryString ? queryString[1] : null;
};
  
}
