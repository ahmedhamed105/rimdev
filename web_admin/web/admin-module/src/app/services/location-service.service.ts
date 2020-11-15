import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
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

   async all_info() 
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
  //console.log(os)
  this.device.deviceOSID={
    id: 0,
    deviceOS: os
  };
}

 
    if(devicename === undefined){  
    this.device.devicetypeID=
    {
      id: 0,
      devtype: 'unknown'
    };
    }else{
    // console.log(devicename)
    this.device.devicetypeID=
    {
      id: 0,
      devtype: 'unknown'
    };
  }


    this.device.devicebrowser=browser;
    this.device.deviceBVersion=browser_version;
    this.device.deviceinfo=browser+browser_version;
    this.device.deviceosversion=os_version;
    this.device.deviceosunknow=userAgent;

    this.device.mobile=isMobilea;
    this.device.tablet=isTableta;
    this.device.desktopDevice=isDesktopDevicea;
    this.device.page= Number(GlobalConstants.pageid);


    this.device.applicationID ={
      id: 0,
      appname: 'Admin'
    };


   
 this.device.deviceip = ''

   console.log("IP "+this.device.deviceip)
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


      await this.insert().then(res => {
     this.mydevice =res;
     
      });
   


  }



async  insert() : Promise<any>{
  
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.Deviceinsert+"/"+GlobalConstants.language;
  let headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE});
let options = { headers: headers };
  return await   this._http.post<any>(urlall,this.device,options).toPromise();
  
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
