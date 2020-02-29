import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { CurrencyComponent } from './currency/currency.component';
import { FlowtypeComponent } from './flowtype/flowtype.component';
import { BlockedComponent } from './blocked/blocked.component';
import { DevicesComponent } from './devices/devices.component';
import { UsersComponent } from './users/users.component';
import { UsermailComponent } from './usermail/usermail.component';
import { UserteleComponent } from './usertele/usertele.component';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';



const routes: Routes = [
  {
    path: '',
    redirectTo : '/dashboard',
    pathMatch: 'full'
},
  {
      path: 'dashboard',
      component: DasboardComponent,
  },
  {
    path: 'usermail',
    component: UsermailComponent,
},
{
  path: 'usertele',
  component: UserteleComponent,
},
  {
    path: 'user',
    component: UsersComponent,
},
  {
    path: 'currency',
    component: CurrencyComponent,
},
{
  path: 'flowtype',
  component: FlowtypeComponent,
},
{
  path: 'devices',
  component: DevicesComponent,
},
{
  path: 'blocked',
  component: BlockedComponent,
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
export const routingComponents=[ErrorDialogComponent,UsertypedropdownComponent,UserteleComponent,UsermailComponent,UsersComponent,DevicesComponent,BlockedComponent,AppComponent,DasboardComponent,CurrencyComponent,FlowtypeComponent,PageNotFoundComponent]
