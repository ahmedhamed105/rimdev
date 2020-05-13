import { Component, OnInit, Renderer2, ElementRef } from '@angular/core';
import { MenushareService } from '../share_data/menushare.service';
import { CookiesService } from '../services/cookies.service';
import { GlobalConstants } from '../GlobalConstants';
import { ComponentService } from '../services/component.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public menus =[];

  public langs =[];

  public display = 0;

  public user ;

  public profileimage;

  public app ;

  public notif =[];

  constructor(private sanitizer: DomSanitizer,public _ComponentService: ComponentService,private cookieService: CookiesService,private _MenushareService:MenushareService) { }




  ngOnInit(): void {

    this._MenushareService.getmenu()
     .subscribe(mymessage => {
      this.menus = mymessage;
     if(mymessage === undefined){
      this.display = 0;
     }else{
      this.display = 1;
     }
   
     });


     this._MenushareService.getlang()
     .subscribe(mymessage => {
      this.langs = mymessage;

     });

     this._MenushareService.getuser()
     .subscribe(userdata => {
       this.user = userdata;
       this._MenushareService.getapp()
       .subscribe(appdata => {
      this.app = appdata;
      this._MenushareService.getnotif()
      .subscribe(notifdata => {
        this.notif= notifdata;
        console.log(this.notif);
      this._ComponentService.getimage(this.user.userID.filesuploadID.id).subscribe(image =>{
      
      
        if(image.size === 0){
          this.profileimage = 'assets/img/avatar04.png';
        }else{
          let unsafeImageUrl = URL.createObjectURL(image);

        this.profileimage = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
        }

  
       
      });
       });
      });
     });



  

  }



  
language(code){
  this.cookieService.language(code);
    }


    signout(){
      this.cookieService.username('',GlobalConstants.rember);
      this.cookieService.usertokean('',GlobalConstants.rember);
      window.location.replace("/login");
        }

}
