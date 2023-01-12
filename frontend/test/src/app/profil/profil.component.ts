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

  public imgURL: any
  public imagePath : any;
  public userFile: any;
  public file: File;

  onFilechange(event: any) {
    console.log(event.target.files[0])
    this.file = event.target.files[0]
    var mimeType = event.target.files[0].type
    if(mimeType.match(/image\/*/) == null){
        console.log("only images are supported");
        return;
    }

    var reader = new FileReader();
        this.imagePath = this.file;
        reader.readAsDataURL(this.file)
        reader.onload = (_event) => {
          this.imgURL = reader.result
        }
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
