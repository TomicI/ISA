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
import { AviokomListComponent } from './aviokom-list/aviokom-list.component'
import { AvioEditComponent } from './avio-edit/avio-edit.component'
import { FormAKUpdateComponent } from './form-akupdate/form-akupdate.component';
import { AerodromComponent } from './aerodrom/aerodrom.component';
import { FormAddAerodromComponent } from './form-add-aerodrom/form-add-aerodrom.component';
import { DestinacijaComponent } from './destinacija/destinacija.component';
import { FormAddDestinacijaComponent } from './form-add-destinacija/form-add-destinacija.component';
import { FormUpdateAerodromComponent } from './form-update-aerodrom/form-update-aerodrom.component';
import { FormAddLetComponent } from "./form-add-let/form-add-let.component";
import { LetComponent } from "./let/let.component";
import { FormUpdateLetComponent } from "./form-update-let/form-update-let.component";
import { FormDodajAviokomComponent } from './form-dodaj-aviokom/form-dodaj-aviokom.component';


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
  { path: 'signup', component: SignUpModalComponent },
{path: 'aviokom-list', component: AviokomListComponent},
{path: 'avio-edit/:id', component: AvioEditComponent},
{path: 'form-akupdate/:id', component: FormAKUpdateComponent},
{path: 'aerodrom/:id', component: AerodromComponent},
{path: 'aerodromAdd', component: FormAddAerodromComponent},
{path: 'aerodromUpdate/:id', component: FormUpdateAerodromComponent},
{path: 'destinacije', component: DestinacijaComponent},
{path: 'destinacijaAdd', component: FormAddDestinacijaComponent},
{path: 'letUpdate/:id', component: FormUpdateLetComponent},
{path: 'letAdd/:id', component: FormAddLetComponent},
{path: 'letovi/:id', component: LetComponent},
{path: 'addAvioKom', component: FormDodajAviokomComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
