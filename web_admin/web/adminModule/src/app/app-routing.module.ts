import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { CurrencyComponent } from './currency/currency.component';
import { BlockedComponent } from './blocked/blocked.component';
import { UsertypedropdownComponent } from './usertypedropdown/usertypedropdown.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { PagesComponent } from './pages/pages.component';



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
    path: 'currency',
    component: CurrencyComponent,
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
export const routingComponents=[PagesComponent,ErrorpageComponent,ErrorDialogComponent,UsertypedropdownComponent,BlockedComponent,AppComponent,DasboardComponent,CurrencyComponent,PageNotFoundComponent]
