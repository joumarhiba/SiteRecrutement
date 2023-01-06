import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Offre } from '../offre/offre';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public p: number = 1;
  public offres: Offre[];
  public offre: Offre;

  constructor(private adminService: AdminService){}


  ngOnInit(): void {
    this.getAllOffres();

  }

  public getAllOffres() : void{
    this.adminService.getAllOffres().subscribe(
      (response: Offre[]) => {
        console.log(response);
        this.offres = response;
      },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }

  public updateStatus(offre: Offre): void {
    this.adminService.updateOffreStatus(offre).subscribe(
      (response: Offre) => {
        console.log(response);
        this.offre = response;
        //this.getAllOffres;
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        console.log(error)
      }
    );
  }
}
