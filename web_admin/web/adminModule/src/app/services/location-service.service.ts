import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient } from '@angular/common/http';
import { DeviceServService } from './device-serv.service';
import { IDevice } from '../objects/IDevice';



@Injectable({
  providedIn: 'root'
})
export class LocationServiceService {

  public device =  <IDevice>{};

  constructor(private deviceService: DeviceDetectorService,private _http:HttpClient,private _DeviceServService:DeviceServService) { }

  getPosition(): Promise<any>
  {
    return new Promise((resolve, reject) => {

      navigator.geolocation.getCurrentPosition(resp => {

          resolve({lng: resp.coords.longitude, lat: resp.coords.latitude});
        },
        err => {
          reject(err);
        });
    });

  }


  all_info()
  {
    
      const deviceInfo = this.deviceService.getDeviceInfo();
      const isMobile = this.deviceService.isMobile();
      const isTablet = this.deviceService.isTablet();
      const isDesktopDevice = this.deviceService.isDesktop();
      const url = location.host;
      const browser = deviceInfo.browser;
      const browser_version=deviceInfo.browser_version;
      const device=deviceInfo.device;
      const os=deviceInfo.os;
      const os_version=deviceInfo.os_version;
      const userAgent=deviceInfo.userAgent;
      if(isDesktopDevice && os==='Windows'){
        this.device.deviceOSID = {id: 1,deviceOS: ''};
      }else{
        this.device.deviceOSID = {id: 6,deviceOS: ''};; 
      }

      if(isDesktopDevice && device==='Desktop'){
        this.device.devicetypeID = {id: 1,devtype:''};
      }else{
        this.device.devicetypeID = {id: 6,devtype: ''};; 
      }



      this.device.deviceinfo=browser+browser_version;
      this.device.deviceosversion=os_version;
      this.device.deviceosunknow=userAgent;

      this._http.get<{ip:string}>('https://jsonip.com')
      .subscribe( data => {
        const ip= data.ip ;
        this.device.deviceip=ip;
        navigator.geolocation.getCurrentPosition(resp => {

          const lng= resp.coords.longitude;
          const lat= resp.coords.latitude;
          this.device.devicelong=lng;
          this.device.devicelatitude= lat;


          this.insertdevice();
        },
        err => {
          const lng= 0;
          const lat= 0;
          this.insertdevice();
        });


      })

    
        
 

  }

   getIPAddress(): Promise<any>
  {
    return new Promise((resolve, reject) => {
     this._http.get<{ip:string}>('https://jsonip.com')
    .subscribe( data => {
      resolve({ip: data.ip});
    })
  });
  }


  insertdevice(){
    console.log(this.device);
    this._DeviceServService.insert(this.device)
    .subscribe(
    data => console.log(data)
    );
  
   } 

  
}
