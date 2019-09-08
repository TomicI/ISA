import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RentacarComponent } from './rentacar/rentacar.component';
import { RentacarListComponent } from './rentacar-list/rentacar-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsComponent } from './forms/forms.component';
import { FilijalaComponent } from './filijala/filijala.component';
import { FilijalaListComponent } from './filijala-list/filijala-list.component';
import { LoginComponent } from './login/login.component';

import { httpInterceptorProviders } from './auth/interceptor';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { PanelComponent } from './panel/panel.component';
import { FormRentComponent } from './form/form-rent/form-rent.component';
import { FormFilComponent } from './form/form-fil/form-fil.component';
import { FormVozComponent } from './form/form-voz/form-voz.component';
import { SignUpModalComponent } from './sign-up-modal/sign-up-modal.component';
import { NavigationComponent } from './navigation/navigation.component';
import { PanelAdminRentComponent } from './panel-admin-rent/panel-admin-rent.component';

import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

import { DataTablesModule } from 'angular-datatables';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AviokomListComponent } from './aviokom-list/aviokom-list.component';
import { AvioEditComponent } from './avio-edit/avio-edit.component';
import { FormAKUpdateComponent } from './form-akupdate/form-akupdate.component';
import { LetComponent } from './let/let.component';
import { KartaComponent } from './karta/karta.component';
import { FormAddKartaComponent } from './form-add-karta/form-add-karta.component';
import { FormAddAerodromComponent } from './form-add-aerodrom/form-add-aerodrom.component';
import { AerodromComponent } from './aerodrom/aerodrom.component';
import { DestinacijaComponent } from './destinacija/destinacija.component';
import { FormAddDestinacijaComponent } from './form-add-destinacija/form-add-destinacija.component';
import { FormUpdateAerodromComponent } from './form-update-aerodrom/form-update-aerodrom.component';
import { AddAviokAerComponent } from './add-aviok-aer/add-aviok-aer.component';
import { FormAddLetComponent } from './form-add-let/form-add-let.component';
import { FormUpdateLetComponent } from './form-update-let/form-update-let.component';
import { FormDodajAviokomComponent } from './form-dodaj-aviokom/form-dodaj-aviokom.component';
import { FormCenComponent } from './form/form-cen/form-cen.component';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CalendarComponent } from './calendar/calendar.component';


import { PanelReservationRentComponent } from './panel-reservation-rent/panel-reservation-rent.component';
import { PanelProfileComponent } from './panel-profile/panel-profile.component';
import { PanelResetpassComponent } from './panel-resetpass/panel-resetpass.component';
import { PanelSettingsComponent } from './panel-settings/panel-settings.component';
import { UserReservationsComponent } from './user-reservations/user-reservations.component';
import { UserFriendsComponent } from './user-friends/user-friends.component';
import { SearchGetComponent } from './search-get/search-get.component';
import { SearchRentComponent } from './search-rent/search-rent.component';
import { ResDetailComponent } from './res-detail/res-detail.component';
import { MapComponent } from './map/map.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { AdminResetComponent } from './admin-reset/admin-reset.component';
import { ReservationDetailsComponent } from './reservation-details/reservation-details.component';
import { ReservationOverviewComponent } from './reservation-overview/reservation-overview.component';
import { AviokomProfilComponent } from './aviokom-profil/aviokom-profil.component';
import { AddFriendsComponent } from './add-friends/add-friends.component';
import { FormKonfLetaComponent } from './form-konf-leta/form-konf-leta.component';
import { KonfigListComponent } from './konfig-list/konfig-list.component';
import { AddSegmentComponent } from './add-segment/add-segment.component';
import { AddKatSedistaComponent } from './add-kat-sedista/add-kat-sedista.component';
import {RouteCheckComponent} from "./route-check/route-check.component";
import { ReservationPreviewComponent } from './reservation-preview/reservation-preview.component';
import { SearchLetComponent } from './search-let/search-let.component';
import {MatMenuModule, MatSortModule} from "@angular/material";
import {NewDealReservationComponent} from "./new-deal-reservation/new-deal-reservation.component";
import { HomeRegisteredComponent } from './home-registered/home-registered.component';
import { InviteComponent } from './invite/invite.component';
import { ListComponent } from './list/list.component';
import {MatFormFieldModule, MatInputModule} from "@angular/material";
import { UnosPutnikaComponent } from './unos-putnika/unos-putnika.component';
import { HotelPretragaComponent } from './hotel-pretraga/hotel-pretraga.component';
import { HotelPrikazComponent } from './hotel-prikaz/hotel-prikaz.component';
import { HotelRezervisiComponent } from './hotel-rezervisi/hotel-rezervisi.component';



@NgModule({
  declarations: [
    AppComponent,
    RentacarComponent,
    RentacarListComponent,
    FormsComponent,
    FilijalaComponent,
    FilijalaListComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
    PanelComponent,
    FormRentComponent,
    FormFilComponent,
    FormVozComponent,
    SignUpModalComponent,
    NavigationComponent,
    PanelAdminRentComponent,
    AppComponent,
    AviokomListComponent,
    AvioEditComponent,
    FormAKUpdateComponent,
    LetComponent,
    KartaComponent,
    FormAddKartaComponent,
    FormAddAerodromComponent,
    AerodromComponent,
    DestinacijaComponent,
    FormAddDestinacijaComponent,
    FormUpdateAerodromComponent,
    AddAviokAerComponent,
    FormAddLetComponent,
    FormUpdateLetComponent,
    FormDodajAviokomComponent,
    FormCenComponent,
    CalendarComponent,
    PanelReservationRentComponent,
    PanelProfileComponent,
    PanelResetpassComponent,
    PanelSettingsComponent,
    UserReservationsComponent,
    UserFriendsComponent,
    SearchGetComponent,
    SearchRentComponent,
    ResDetailComponent,
    MapComponent,
    VehicleComponent,
    AviokomProfilComponent,
    VehicleComponent,
    AdminResetComponent,
    ReservationDetailsComponent,
    ReservationOverviewComponent,
    AddFriendsComponent,
    RouteCheckComponent,
    AddFriendsComponent,
    FormKonfLetaComponent,
    KonfigListComponent,
    AddSegmentComponent,
    AddKatSedistaComponent,
    ReservationPreviewComponent,
    SearchLetComponent,
    NewDealReservationComponent,
    HomeRegisteredComponent,
    InviteComponent,
    ListComponent,
    SearchLetComponent,
    UnosPutnikaComponent,
    HotelPretragaComponent,
    HotelPrikazComponent,
    HotelRezervisiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    DataTablesModule,
    MatTableModule,
    MatSortModule,
    MatMenuModule,
    MatPaginatorModule,
    MatAutocompleteModule,
    NgbModule.forRoot(),
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
  
  ],
  providers: [httpInterceptorProviders],
  entryComponents: [SignupComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
