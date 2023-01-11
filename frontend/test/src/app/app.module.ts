import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {NgxPaginationModule} from 'ngx-pagination';

import { AppComponent } from './app.component';
import {HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

import {MatDividerModule} from '@angular/material/divider';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'
import { FormsModule } from '@angular/forms';
import { CompanyComponent } from './Company/company.component';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { ProfilComponent } from './profil/profil.component';
import { OffreComponent } from './offre/offre.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon'

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    HomeComponent,
    AdminComponent,
    ProfilComponent,
    OffreComponent,
    AuthenticationComponent
  ],
  imports: [
    MatSlideToggleModule,
    NgxPaginationModule,
    BrowserModule,
    MatIconModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDividerModule,
    NgbModule, FormsModule,
      RouterModule,
      MatToolbarModule,
      AppRoutingModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent, CompanyComponent]
})
export class AppModule { }
