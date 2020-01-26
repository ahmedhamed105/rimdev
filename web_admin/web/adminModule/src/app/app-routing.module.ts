import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { CurrencyComponent } from './currency/currency.component';
import { FlowtypeComponent } from './flowtype/flowtype.component';
import { BlockedComponent } from './blocked/blocked.component';



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
    path: 'currency',
    component: CurrencyComponent,
},
{
  path: 'flowtype',
  component: FlowtypeComponent,
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
export const routingComponents=[BlockedComponent,AppComponent,DasboardComponent,CurrencyComponent,FlowtypeComponent,PageNotFoundComponent]
