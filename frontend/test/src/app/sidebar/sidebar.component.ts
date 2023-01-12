import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  constructor(private httpClient: HttpClient){}

  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;

  ngOnInit(): void {

  }



}
