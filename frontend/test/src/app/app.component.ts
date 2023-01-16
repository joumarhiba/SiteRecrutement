import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HomeService } from './home/home.service';
import { Offre } from './offre/offre';
import { Company } from './Company/company';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

   constructor(private homeService: HomeService, private _snackBar: MatSnackBar){}

   public offres: Offre[];
   public companies: Company[];
   public editoffre?: Offre ;
   public loginCompany?: Company;
   public credentials = {
     email : '',
     password : ''
   }
   public aCredentials = {
    email : '',
    password : ''
  }
   public loggedIn = false;
   public AdminloggedIn = false;

   openSnackBar(message: string) {
    this._snackBar.open(message);
  }


   ngOnInit() {
    this.loggedIn = this.homeService.isLoggedIn();
    this.AdminloggedIn = this.homeService.isAdminLoggedIn();
   }

   logoutUser() {
    this.homeService.logout()
    location.reload()

   }


   public onOpenModal(company?: Company,mode? : string) : void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'signUp'){
      button.setAttribute('data-target','#SignUpModal')
    }
    if(mode === "loginCompany") {
      document.getElementById('close-modal')?.click();
      this.loginCompany = company;
      button.setAttribute('data-target', '#LoginCompanyModal')
    }
    container?.appendChild(button);
    button.click();
  }



  public addCompany(signUpForm: NgForm): void
  {
    document.getElementById('close-modal')?.click();
  this.homeService.addCompany(signUpForm.value).subscribe(
    (response: Company) => {
      console.log(response)
      signUpForm.reset
    },
    (error: HttpErrorResponse) => {
      console.log(error);
      console.log(signUpForm);
      alert(error.message);
      }
  );
  }


// Do login for company
  public OnSubmit():void  {
    if(this.credentials.email != '' && this.credentials.password != '') {
        this.homeService.generateCompanyToken(this.credentials).subscribe(
          (response: any) => {
            console.log(response.token);
            this.homeService.loginCompany(response.token);
            window.location.href='/company'

          },
          (error: HttpErrorResponse) => {
            console.log(error);
            }
          );
    }
    else {
      console.log("Fields are empty !");

    }
}


public LoginAdmin():void  {
  if(this.aCredentials.email != '' && this.aCredentials.password != '') {
      this.homeService.generateAdminToken(this.aCredentials).subscribe(
        (response: any) => {
          console.log(response.token);
          this.homeService.loginAdmin(response.token);
          window.location.href='/admin'

        },
        (error: HttpErrorResponse) => {
          console.log(error);
          }
        );
  }
  else {
    console.log("Fields are emplty !");

  }
}



}
