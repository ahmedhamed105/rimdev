import { Injectable } from '@angular/core';
import { GlobalConstants } from '../GlobalConstants';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class CookiesService {

  isConsented = false;

    constructor(private storage:LocalStorageService) {}

    /**
     * delete cookie
     * @param name
     */
    public deleteCookie(name) {
      this.storage.clear(name);
      //  this.setCookie(name, '', -1);
    }

    /**
     * get cookie
     * @param {string} name
     * @returns {string}
     */
    public getCookie(name: string) {
   //     const ca: Array<string> = document.cookie.split(';');
   //     const caLen: number = ca.length;
   //     const cookieName = `${name}=`;
   //     let c: string;

   //     alert(document.cookie);
    //    for (let i  = caLen -1  ; i > 0; i -= 1) {
         
   //         c = ca[i].replace(/^\s+/g, '');
    //        if (c.indexOf(cookieName) === 0) {
   //             return c.substring(cookieName.length, c.length);
   //         }
    //    }
   //     return '';
var out = this.storage.retrieve(name);
   if(out === null){

return '';
   }
   return out
    }

    /**
     * set cookie
     * @param {string} name
     * @param {string} value
     * @param {number} expireDays
     * @param {string} path
     */
    public setCookie(name: string, value: string, expireDays: number, path: string = '') {
   //     const d: Date = new Date();
   //     d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    //    const expires = `expires=${d.toUTCString()}`;
   //     const cpath = '/'//path ? `; path=${path}` : '';
  //      document.cookie = `${name}=${value}; ${expires}${cpath} `;

  this.storage.store(name, value);
     
    }

    /**
     * consent
     * @param {boolean} isConsent
     * @param e
     * @param {string} COOKIE
     * @param {string} EXPIRE_DAYS
     * @returns {boolean}
     */
    public consent(isConsent: boolean, e: any, COOKIE: string, EXPIRE_DAYS: number) {
        if (!isConsent) {
            return this.isConsented;
        } else if (isConsent) {
            this.setCookie(COOKIE, '1', EXPIRE_DAYS);
            this.isConsented = true;
            e.preventDefault();
        }
    }



    language(code){
        GlobalConstants.language= code;
        this.deleteCookie('language');
        this.setCookie( 'language', GlobalConstants.language,10,'' ); // To Set Cookie
        location.reload();
      
      }

      
      checkboxrember(rember){
        GlobalConstants.rember= rember;
          this.deleteCookie('rember');
          this.setCookie( 'rember', GlobalConstants.rember,10,'' ); // To Set Cookie
  
      //  location.reload();
      }
      username(user,rember){
        GlobalConstants.USERNAME= user;
        if(rember === '1' ){

          this.deleteCookie('username');
          this.setCookie( 'username', GlobalConstants.USERNAME,10,'' ); // To Set Cookie
        }

      //  location.reload();
      }

      usertokean(user,rember){
        GlobalConstants.USERTOKEANkey= user;
        if(rember === '1' ){
        this.deleteCookie('usertokean');
        this.setCookie( 'usertokean', GlobalConstants.USERTOKEANkey,10,'' ); // To Set Cookie
        }
      //  location.reload();
      }

}
