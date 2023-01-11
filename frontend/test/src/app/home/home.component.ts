import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http'
import { Offre } from '../offre/offre';
import { Company } from '../Company/company';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})

export class HomeComponent implements OnInit {

  p: number = 1;
  public offres: Offre[];
  public companies: Company[];
  public editoffre?: Offre ;
  public loginCompany?: Company;
  public credentials = {
    email : '',
    password : ''
  }

  constructor(private homeService: HomeService){}

  ngOnInit(): void {
    this.getValidatedOffres();
  }



  getValidatedOffres(): void {
    this.homeService.getValidatedOffres().subscribe(
      (response: Offre[]) => {
          this.offres = response;
      },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }


  public onOpenModal(offre?: Offre, company?: Company, mode? : string) : void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'readMore'){
      this.editoffre = offre;
      button.setAttribute('data-target','#ReadMoreModal')
    }
    if(mode === "loginCompany") {
      document.getElementById('close-modal')?.click();
      this.loginCompany = company;
      button.setAttribute('data-target', '#LoginCompanyModal')
    }
    container?.appendChild(button);
    button.click();
  }


  public getOffreDetails(editoffre: Offre): void
  {
  this.homeService.getOffreDetails(editoffre.id).subscribe(
    (response) => {
      console.log(response)
      console.log(editoffre.id);
      this.getCompanies
      return response
    },
    (error: HttpErrorResponse) => {
      console.log(error);
      alert(error.message + "offre Id == "+ editoffre.id);
      return error
      }
  );
  }

  public getCompanies(): void {
    this.homeService.getCompanies().subscribe(
      (response: Company[]) => {
          this.companies = response;
      },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }

}
