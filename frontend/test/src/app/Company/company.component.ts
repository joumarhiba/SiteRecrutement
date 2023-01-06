import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Company } from '../Company/company';
import { CompanyService } from '../Company/company.service';
import { Offre } from '../offre/offre';

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

  constructor(private companyService: CompanyService){}
  ngOnInit(): void {
    this.getOffreByCompany(this.company)
  }


  public editModal(offre: Offre, mode : string) : void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'edit'){
      button.setAttribute('data-target','#editModal')
    }

    container?.appendChild(button);
    button.click();
  }


public getCompanies(): void {
  this.companyService.getCompanies().subscribe(
    (response: Company[]) => {
        this.companies = response;
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
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
