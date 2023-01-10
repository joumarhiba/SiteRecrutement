import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HomeService } from './home/home.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

   constructor(private homeService: HomeService){}

   public loggedIn = false;

   ngOnInit() {
    this.loggedIn = this.homeService.isLoggedIn();
   }

   logoutUser() {
    this.homeService.logout()
    location.reload()
   }


}
