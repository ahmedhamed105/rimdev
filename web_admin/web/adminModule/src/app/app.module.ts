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


@NgModule({
  declarations: [
    routingComponents,
    FlowtypeComponent,
    BlockedComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    DeviceDetectorModule.forRoot()
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
