import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { CompanyComponent } from './Company/company.component';
import { HomeComponent } from './home/home.component';
import { OffreComponent } from './offre/offre.component';
import { ProfilComponent } from './profil/profil.component';

const routes: Routes = [
  { path:'company', component:CompanyComponent },
  { path: 'home', component: HomeComponent },
  { path: 'addOffre', component: OffreComponent},
  { path: 'profil', component: ProfilComponent},
  { path: '', redirectTo: '/AdminComponent', pathMatch: 'full' },
  { path: 'admin', component: AdminComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
