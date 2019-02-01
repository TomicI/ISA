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

import { DataTablesModule } from 'angular-datatables';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


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



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    DataTablesModule,
    MatTableModule,
    MatPaginatorModule,
    NgbModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [httpInterceptorProviders],
  entryComponents: [SignupComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
