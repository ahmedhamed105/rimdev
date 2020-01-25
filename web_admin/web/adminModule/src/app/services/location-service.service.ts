import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient } from '@angular/common/http';
import { DeviceServService } from './device-serv.service';
import { IDevice } from '../objects/IDevice';
import { IdeviceOS } from '../objects/IdeviceOS';
import { Idevicetype } from '../objects/Idevicetype';



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

  _urlgetallos='http://192.168.3.76:8081/DeviceOS/all';
  _urlgetalltype='  http://192.168.3.76:8081/DeviceType/all';

  public deviceos = [];
  public devicetype = [];

  all_info() :any
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



      this._http.get<IdeviceOS[]>(this._urlgetallos)
      .subscribe(data => {this.deviceos = data;

      for(var i = 0; i < this.deviceos.length; i++) {
        console.log(this.deviceos[i].deviceOS);
          if (this.deviceos[i].deviceOS == os) {
            this.device.deviceOSID=this.deviceos[i];
              break;
          }
      }  

      this._http.get<Idevicetype[]>(this._urlgetalltype)
      .subscribe(data1 => {this.devicetype = data1;

      for(var i = 0; i < this.devicetype.length; i++) {
        console.log(this.devicetype[i].devtype);
          if (this.devicetype[i].devtype == devicename) {
            this.device.devicetypeID=this.devicetype[i];
              break;
          }
      }


      this.device.devicebrowser=browser;
      this.device.deviceBVersion=browser_version;
      this.device.deviceinfo=browser+browser_version;
      this.device.deviceosversion=os_version;
      this.device.deviceosunknow=userAgent;

      this.device.isMobile=isMobilea;
      this.device.isTablet=isTableta;
      this.device.isDesktopDevice=isDesktopDevicea;


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


      
      

    
    });

    });




    console.log('ahmed '+devicename);
    return devicename;

    
        
 

  }




  insertdevice(){
    console.log(this.device);
    this._DeviceServService.insert(this.device)
    .subscribe(
    data => console.log(data)
    );
  
   } 

  
}
