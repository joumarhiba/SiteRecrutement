import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  public href: string = "";

   constructor(location: Location,private router: Router){}

   public style(): void
   {
    this.href = this.router.url;
  console.log("the url : "+this.router.url);
     if(this.href === '/company'){
        const a = document.getElementById('companyID')
        if(a!= null){
          a.style.display = 'none';
        }
     }
   }
  // ngOnInit() {
  //     this.href = this.router.url;
  //     console.log("the url : "+this.router.url);
  // }




}
