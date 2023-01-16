import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Company } from '../Company/company';
import { CompanyService } from '../Company/company.service';
import { Offre } from '../offre/offre';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import { OffreService } from '../offre/offre.service';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit{
  public companies: Company[];
  public offres: Offre[];
  public company : Company = {
    id:3,
    email:'entreprise1@gmail.com',
    password: '12345678',
    adresse: "azertyui",
    telephone:'0612345678',
    userRole:'COMPANY'
  }

  public offreToUpdate: Offre = {
    id:0,
    title:'',
    description:'',
    profil:'',
    niveau:'',
    ville:'',
    salaire:'',
    status:false,
    company_id:0,
    admin_id:1
  }

  constructor(private companyService: CompanyService, private offreService: OffreService){}
  ngOnInit(): void {
    this.getOffreByCompany(this.company)
  }



  public edit(offre: Offre) : void{
    this.offreToUpdate = offre;
  }

  public updateOffre() {
    document.getElementById('close-modal')?.click();
    this.offreService.updateOffre(this.offreToUpdate).subscribe(
  (response: Offre) => {
  console.log("the response "+response);
  window.location.reload()
  },
    (error: HttpErrorResponse) => {
    alert(error.message);
  }
)
  }


public getCompanies(): void {
  this.companyService.getCompanies().subscribe(
    (response: Company[]) => {
        this.companies = response;
    },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
  );
}

public getOffreByCompany(company: Company) {
  this.companyService.findOffreByCompany(company).subscribe(
    (response: Offre[]) => {
        this.offres = response;
    },
      (error: HttpErrorResponse) => {
        console.log(this.offres);
        console.log(error);

      }
  );
}

}
