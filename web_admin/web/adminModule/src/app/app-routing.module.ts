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
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { PagesComponent } from './pages/pages.component';
import { ExampleRouteResolver } from './ExampleRouteResolver';



const routes: Routes = [
  {
    path: '',
    redirectTo : '/dashboard/P/1',
    pathMatch: 'full'
},
  {
      path: 'dashboard/:type/:id',
      component: DasboardComponent,
  },
  {
    path: 'page/:type/:id',
    component: PagesComponent,
},
  {
    path: 'user/:type/:id',
    component: UsersComponent,
},
  {
    path: 'currency/:type/:id',
    component: CurrencyComponent,
},
{
  path: 'flowtype/:type/:id',
  component: FlowtypeComponent,
},
{
  path: 'devices/:type/:id',
  component: DevicesComponent,
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
export const routingComponents=[PagesComponent,ErrorpageComponent,ErrorDialogComponent,UsertypedropdownComponent,UserteleComponent,UsermailComponent,UsersComponent,DevicesComponent,BlockedComponent,AppComponent,DasboardComponent,CurrencyComponent,FlowtypeComponent,PageNotFoundComponent]
