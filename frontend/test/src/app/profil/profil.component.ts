import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProfilService } from './profil.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {

  constructor(private profilService: ProfilService, private snackBar: MatSnackBar){}

  public file: File;

  onFilechange(event: any) {
    console.log(event.target.files[0])
    this.file = event.target.files[0]
  }

  openSnackBar(message: string) {
    this.snackBar.open(message);
  }


  upload() {
    if (this.file) {
      this.profilService.uploadfile(this.file).subscribe(resp => {
        console.log(this.file);

      })
    } else {
      alert("Please select a file first")
    }
  }

}
