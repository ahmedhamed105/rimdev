import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule , HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpErrorInterceptor } from './Error/HttpErrorInterceptor';
import { AgGridModule } from 'ag-grid-angular';
import {FileUploadModule} from 'ng2-file-upload';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { PasswordtableComponent } from './passwordtable/passwordtable.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {NgxWebstorageModule} from 'ngx-webstorage';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


@NgModule({
  declarations: [
    routingComponents,
    HeaderComponent,
    FooterComponent
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    AgGridModule.withComponents([
      UsertypedropdownComponent,
      PasswordtableComponent
    ]),
    FileUploadModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule

  ],
  schemas: [ 
    CUSTOM_ELEMENTS_SCHEMA
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
