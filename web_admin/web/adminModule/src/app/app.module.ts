import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule , HTTP_INTERCEPTORS } from '@angular/common/http';
import { DasboardComponent } from './dasboard/dasboard.component';
import { CurrencyComponent } from './currency/currency.component';
import { HttpErrorInterceptor } from './Error/HttpErrorInterceptor';
import { FlowtypeComponent } from './flowtype/flowtype.component';
import { DeviceDetectorModule } from 'ngx-device-detector';
import { BlockedComponent } from './blocked/blocked.component';
import { DevicesComponent } from './devices/devices.component';
import { AgGridModule } from 'ag-grid-angular';
import { UsersComponent } from './users/users.component';
import { NgxMaskModule } from 'ngx-mask';
import { IConfig } from 'ngx-mask';
import {FileUploadModule} from 'ng2-file-upload';
import { UsermailComponent } from './usermail/usermail.component';
import { UserteleComponent } from './usertele/usertele.component';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = {};


@NgModule({
  declarations: [
    routingComponents,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    DeviceDetectorModule.forRoot(),
    AgGridModule.withComponents([
      UsertypedropdownComponent
    ]),
    NgxMaskModule.forRoot(options),
    FileUploadModule

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
