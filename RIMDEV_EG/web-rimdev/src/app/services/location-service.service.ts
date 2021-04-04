import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { GlobalConstants } from '../GlobalConstants';
import { DeviceDetectorService } from 'ngx-device-detector';
import { promise } from 'selenium-webdriver';


@Injectable({
  providedIn: 'root'
})
export class LocationServiceService {


  public device =  <IDevice>{};

  constructor(private deviceService: DeviceDetectorService,private _http:HttpClient) { }

  



  public deviceos = [];
  public devicetype = [];
  public logintype = [];

   async all_info():Promise<IDevice>
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

    this._http.get("http://api.ipify.org/?format=json").subscribe((res:any)=>{
      this.device.deviceip= res.ip;
    });
   

  this.device.devicecode = GlobalConstants.PCCODE;
  
    await this.getnavigation()
    .then(position => {
      this.device.devicelong=position.coords.longitude;
      this.device.devicelatitude= position.coords.latitude;
    }).catch((error) => {
      console.log(error);

      this.device.devicelong=0;
      this.device.devicelatitude= 0;
     });
   

   //   console.log('navg '+this.device.devicelong);


return this.device;

  }


async  getnavigation():Promise<any>{

  return await new Promise(function (resolve, reject) {
    navigator.geolocation.getCurrentPosition(resolve, ({code, message}) =>
    reject(Object.assign(new Error(message), {name: "PositionError", code})), {
      enableHighAccuracy: true,
      timeout: 10000,
      maximumAge: 0
  });
  });
  
}


}