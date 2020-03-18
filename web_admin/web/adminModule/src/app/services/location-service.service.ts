import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import { HttpClient } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { IdeviceOS } from '../objects/IdeviceOS';
import { Idevicetype } from '../objects/Idevicetype';
import { Router } from '@angular/router';
import { devicetoken } from '../objects/devicetoken';
import { GlobalConstants } from '../GlobalConstants';



@Injectable({
  providedIn: 'root'
})
export class LocationServiceService {

  public device =  <IDevice>{};

  constructor(private router: Router,private deviceService: DeviceDetectorService,private _http:HttpClient) { }

  



  public deviceos = [];
  public devicetype = [];
  public mydevice;

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

      this.device.mobile=isMobilea;
      this.device.tablet=isTableta;
      this.device.desktopDevice=isDesktopDevicea;
      this.device.page=page;



      });


 //   await   this.getip().then(data => {
 //       this.device.deviceip=data['ip'];
  //    });

  this.device.deviceip = '192.168.1.1';
  
      await this.getnavigation()
      .then(position => {
        this.device.devicelong=position.coords.longitude;
        this.device.devicelatitude= position.coords.latitude;
      });


      await this.insert().then(res => {
     this.mydevice =res;
        if(res['devicestatusID']['id'] === 2){
          this.router.navigate(['/blocked']);
        }
      });
   


  }



async  insert() : Promise<devicetoken>{
  
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.portuser+GlobalConstants.Deviceinsert+"/"+GlobalConstants.language;
  return await   this._http.post<devicetoken>(urlall,this.device).toPromise();
  
}


async  getos() : Promise<IdeviceOS[]>{


  // console.log('finish3');
  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.portuser+GlobalConstants.Deviceos+"/"+GlobalConstants.language;

  return await   this._http.get<IdeviceOS[]>(urlall).toPromise();
  
}

async  gettype() : Promise<Idevicetype[]>{

 // console.log('finish5');
 var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.portuser+GlobalConstants.Devicetype+"/"+GlobalConstants.language;

  return await   this._http.get<Idevicetype[]>(urlall).toPromise();
  
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
