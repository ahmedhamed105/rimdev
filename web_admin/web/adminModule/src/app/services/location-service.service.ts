import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { IdeviceOS } from '../objects/IdeviceOS';
import { Idevicetype } from '../objects/Idevicetype';
import { Router } from '@angular/router';
import { devicetoken } from '../objects/devicetoken';



@Injectable({
  providedIn: 'root'
})
export class LocationServiceService {

  public device =  <IDevice>{};

  constructor(private router: Router,private deviceService: DeviceDetectorService,private _http:HttpClient) { }

  

  _urlgetallos='http://localhost:8081/DeviceOS/all';
  _urlgetalltype='  http://localhost:8081/DeviceType/all';
  _urlpost='http://localhost:8081/Device/saveorupdate';

  public deviceos = [];
  public devicetype = [];
  public status :devicetoken;

   async all_info(page : number) 
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


    //  console.log('finish1');
      await   this.getos().then(res => {
        
        this.deviceos=res;
    //     console.log('finish2');
    //     console.log(this.deviceos);
        for(var i = 0; i < this.deviceos.length; i++) {
       //   console.log(this.deviceos[i].deviceOS);
            if (this.deviceos[i].deviceOS == os) {
              this.device.deviceOSID=this.deviceos[i];
                break;
            }
        }  
  
      //  console.log('finish4');
 
 
      });

     await this.gettype().then(data => {

        this.devicetype = data;

      for(var i = 0; i < this.devicetype.length; i++) {
      //  console.log(this.devicetype[i].devtype);
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
      this.device.page=page;



      });


    await   this.getip().then(data => {
        this.device.deviceip=data['ip'];
      });


      await this.getnavigation()
      .then(position => {
        this.device.devicelong=position.coords.longitude;
        this.device.devicelatitude= position.coords.latitude;
      });


      await this.insert().then(res => {
        this.status =res;
        if(this.status.status !== 0){
          this.router.navigate(['/blocked']);
        }
      });
   


  }



async  insert() : Promise<devicetoken>{

  // console.log('finish3');
  return await   this._http.post<devicetoken>(this._urlpost,this.device).toPromise();
  
}


async  getos() : Promise<IdeviceOS[]>{

  // console.log('finish3');
  return await   this._http.get<IdeviceOS[]>(this._urlgetallos).toPromise();
  
}

async  gettype() : Promise<Idevicetype[]>{

 // console.log('finish5');
  return await   this._http.get<Idevicetype[]>(this._urlgetalltype).toPromise();
  
}


async  getip() : Promise<string>{

//  console.log('finish ip');
  return await   this._http.get<string>('https://jsonip.com').toPromise();
  
}


async  getnavigation():Promise<any>{

  return await new Promise(function (resolve, reject) {
    navigator.geolocation.getCurrentPosition(resolve, reject);
  });
  
}




  
}
