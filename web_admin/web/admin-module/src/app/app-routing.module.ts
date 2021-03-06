import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { BlockedComponent } from './blocked/blocked.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { PagesComponent } from './pages/pages.component';
import { PasswordtableComponent } from './passwordtable/passwordtable.component';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { PasswordStrengthComponent } from './password-strength/password-strength.component';



const routes: Routes = [
  {
    path: '',
    redirectTo : 'login',
    pathMatch: 'full'
},
  {
      path: 'login',
      component: LoginpageComponent,
  },
  {
      path: 'dashboard',
      component: DasboardComponent,
  },
  {
    path: 'page/:type/:id',
    component: PagesComponent,
},
{
  path: 'blocked',
  component: BlockedComponent,
},
{
  path: 'error',
  component: ErrorpageComponent,
},
{
  path: '**',
  component: PageNotFoundComponent,
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[LoginpageComponent,PasswordtableComponent,PasswordStrengthComponent,UsertypedropdownComponent,PasswordtableComponent,PagesComponent,ErrorpageComponent,ErrorDialogComponent,BlockedComponent,AppComponent,DasboardComponent,PageNotFoundComponent]
