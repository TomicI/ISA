import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RentacarComponent } from './rentacar/rentacar.component';
import { RentacarListComponent } from './rentacar-list/rentacar-list.component';
import { FormsComponent } from './forms/forms.component';
import { FilijalaComponent } from './filijala/filijala.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PanelComponent } from './panel/panel.component';
import { SignUpModalComponent } from './sign-up-modal/sign-up-modal.component';
import { FormVozComponent } from './form/form-voz/form-voz.component';
import { FormRentComponent } from './form/form-rent/form-rent.component';



const routes: Routes = [
  { path: '', redirectTo: '/rentacarall', pathMatch: 'full' },
  { path: 'rentacar/:id', component: RentacarComponent },
  { path: 'filijala/:id', component: FilijalaComponent },
  { path: 'rentacarall', component: RentacarListComponent },
  { path: 'rentacarform', component: FormsComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'panel', component: PanelComponent,
    children: [
      { path: 'vozForm', component: FormVozComponent },
      { path: 'rentForm', component: FormRentComponent }
    ]
  },
  { path: 'signup', component: SignUpModalComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
