import {Routes, RouterModule} from '@angular/router';
import {RentacarListComponent} from './rentacar-list/rentacar-list.component';
import {FormsComponent} from './forms/forms.component';
import {FilijalaComponent} from './filijala/filijala.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {PanelComponent} from './panel/panel.component';
import {SignUpModalComponent} from './sign-up-modal/sign-up-modal.component';
import {AviokomListComponent} from './aviokom-list/aviokom-list.component'
import {AvioEditComponent} from './avio-edit/avio-edit.component'
import {FormAKUpdateComponent} from './form-akupdate/form-akupdate.component';
import {AerodromComponent} from './aerodrom/aerodrom.component';
import {FormAddAerodromComponent} from './form-add-aerodrom/form-add-aerodrom.component';
import {DestinacijaComponent} from './destinacija/destinacija.component';
import {FormAddDestinacijaComponent} from './form-add-destinacija/form-add-destinacija.component';
import {FormUpdateAerodromComponent} from './form-update-aerodrom/form-update-aerodrom.component';
import {FormAddLetComponent} from './form-add-let/form-add-let.component';
import {LetComponent} from './let/let.component';
import {FormUpdateLetComponent} from './form-update-let/form-update-let.component';
import {FormDodajAviokomComponent} from './form-dodaj-aviokom/form-dodaj-aviokom.component';
import {PanelProfileComponent} from './panel-profile/panel-profile.component';
import {PanelSettingsComponent} from './panel-settings/panel-settings.component';
import {UserReservationsComponent} from './user-reservations/user-reservations.component';
import {UserFriendsComponent} from './user-friends/user-friends.component';
import {SearchGetComponent} from './search-get/search-get.component';
import {SearchRentComponent} from './search-rent/search-rent.component';
import {PanelAdminRentComponent} from './panel-admin-rent/panel-admin-rent.component';
import {PanelReservationRentComponent} from './panel-reservation-rent/panel-reservation-rent.component';
import {VehicleComponent} from './vehicle/vehicle.component';
import {NgModule} from "@angular/core";
import {AviokomProfilComponent} from "./aviokom-profil/aviokom-profil.component";
import {AdminResetComponent} from "./admin-reset/admin-reset.component";
import {AuthGuard} from "./security/auth.guard";
import {ReservationDetailsComponent} from "./reservation-details/reservation-details.component";
import {ReservationOverviewComponent} from "./reservation-overview/reservation-overview.component";
import {AddFriendsComponent} from "./add-friends/add-friends.component";
import {FormKonfLetaComponent} from "./form-konf-leta/form-konf-leta.component";
import {KonfigListComponent} from "./konfig-list/konfig-list.component";
import {AddKatSedistaComponent} from "./add-kat-sedista/add-kat-sedista.component";
import {AddSegmentComponent} from "./add-segment/add-segment.component";
import {SearchLetComponent} from "./search-let/search-let.component";
import {KartaComponent} from "./karta/karta.component";
import {NewDealReservationComponent} from "./new-deal-reservation/new-deal-reservation.component";
import {HomeRegisteredComponent} from "./home-registered/home-registered.component";
import {InviteComponent} from "./invite/invite.component";
import {ListComponent} from "./list/list.component";
import {UnosPutnikaComponent} from "./unos-putnika/unos-putnika.component";
import {BrzaRezervacijaComponent} from "./brza-rezervacija/brza-rezervacija.component";
import {ResDetailComponent} from "./res-detail/res-detail.component";
import {AviokomapnijaProfilComponent} from "./aviokomapnija-profil/aviokomapnija-profil.component"
import {BrzeRezervacijeListaComponent} from "./brze-rezervacije-lista/brze-rezervacije-lista.component";

const routes: Routes = [
  {path: '', redirectTo: '/travel', pathMatch: 'full'},
  {path: 'rentacarform', component: FormsComponent},
  {
    path: 'travel', component: HomeComponent,
    children: [
      {
        path: 'rentacar', component: SearchRentComponent,
        children: [
          {path: '', component: RentacarListComponent},
          {path: 'branch', component: FilijalaComponent},
          {path: 'search', component: SearchGetComponent},
          {path: 'vehicle', component: VehicleComponent},
          {path: 'reservation', component: ResDetailComponent},
        ]
      },
      {path: 'flights', component: SearchLetComponent,
        children:[
          {path: '', component: SearchLetComponent},
          {path: 'tickets/:id', component: BrzeRezervacijeListaComponent}
        ]
      },

    ]

  },


  {
    path: 'homeReg', component: HomeRegisteredComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_USER_REG"]},
    children: [
      {path: '', redirectTo: 'reservations', pathMatch: 'full'},
      {
        path: 'reservations',
        component: UserReservationsComponent,
        canActivate: [AuthGuard],
        data: {roles: ["ROLE_USER_REG"]},
        children: [
          {path: '', component: ReservationOverviewComponent},
          {path: 'details', component: ReservationDetailsComponent}
        ]
      },
      {path: 'friends', component: UserFriendsComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_USER_REG"]}},
      {path: 'invites', component: InviteComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_USER_REG"]}},
    ]

  },
  {path: 'companies', component: ListComponent},

  {path: 'login', component: LoginComponent},
  {
    path: 'panel', component: PanelComponent,
    children: [
      {path: '', redirectTo: 'adminpanel', pathMatch: 'full'},
      {
        path: 'adminpanel',
        component: PanelAdminRentComponent,
        canActivate: [AuthGuard],
        data: {roles: ['ROLE_ADMIN_RENT', 'ROLE_ADMIN_AVIO', 'ROLE_ADMIN_HOTEL']}
      },
      {
        path: 'adminres',
        component: PanelReservationRentComponent,
        canActivate: [AuthGuard],
        data: {roles: ['ROLE_ADMIN_RENT', 'ROLE_ADMIN_AVIO', 'ROLE_ADMIN_HOTEL']}
      },
    ]
  },
  {
    path: 'newDeal',
    component: NewDealReservationComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_ADMIN_RENT']}
  },
  {path: 'profile', component: PanelProfileComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_USER_REG"]}},
  {path: 'settings', component: PanelSettingsComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_USER_REG"]}},
  {path: 'signup', component: SignUpModalComponent},


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
  {path: 'letovi', component: LetComponent},
  {path: 'aviokompanijaProfil/:id', component: AviokomProfilComponent},
  {path: 'addAviokom', component: FormDodajAviokomComponent},
  {path: 'aviokompanija/:id', component: AviokomapnijaProfilComponent},
  {
    path: 'resetPassword',
    component: AdminResetComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_ADMIN_RENT', 'ROLE_ADMIN_AVIO', 'ROLE_ADMIN_HOTEL']}
  },
  {path: 'addFriends', component: AddFriendsComponent},
  {path: 'addKonf/:id', component: FormKonfLetaComponent},
  {path: 'konfig-list/:id', component: KonfigListComponent},
  {path: 'addKatSed/:id', component: AddKatSedistaComponent},
  {path: 'addSegment/:id', component: AddSegmentComponent},

  {path: 'karta/:id', component: KartaComponent},
  {path: 'unosPutnika/:kartaID/:brPutnika', component: UnosPutnikaComponent},
  {path: 'brzaRezervacija/:id', component: BrzaRezervacijaComponent},
  {path: 'tickets/:id', component: BrzeRezervacijeListaComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
