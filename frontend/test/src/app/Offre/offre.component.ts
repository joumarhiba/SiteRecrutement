import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Offre } from './offre';
import { OffreService } from './offre.service';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent {

  constructor(private offreService: OffreService) {}

  public addOffre(addOffreForm: NgForm) {
      this.offreService.addOffre(addOffreForm.value).subscribe(
        (response: Offre) => {
          console.log(response);
        },
        (error: HttpErrorResponse) => {
          console.log(error);
          console.log(addOffreForm);
          alert(error.message);
          }
      )
  }


}
