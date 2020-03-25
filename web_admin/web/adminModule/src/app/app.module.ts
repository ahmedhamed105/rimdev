import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule , HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpErrorInterceptor } from './Error/HttpErrorInterceptor';
import { DeviceDetectorModule } from 'ngx-device-detector';
import { AgGridModule } from 'ag-grid-angular';
import { NgxMaskModule } from 'ngx-mask';
import { IConfig } from 'ngx-mask';
import {FileUploadModule} from 'ng2-file-upload';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { PasswordtableComponent } from './passwordtable/passwordtable.component';
import { PasswordStrengthComponent } from './password-strength/password-strength.component';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = {};


@NgModule({
  declarations: [
    routingComponents,
    PasswordtableComponent,
    PasswordStrengthComponent
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    DeviceDetectorModule.forRoot(),
    AgGridModule.withComponents([
      UsertypedropdownComponent,
      PasswordtableComponent
    ]),
    NgxMaskModule.forRoot(options),
    FileUploadModule,
    MatDialogModule,
    BrowserAnimationsModule

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  entryComponents: [ErrorDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
