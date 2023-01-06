import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../Company/company';
import { Profil } from './Profil';

@Injectable({
  providedIn: 'root'
})
export class ProfilService {


  constructor(private http: HttpClient) { }

  public uploadfile(file: File) {
    let formParams = new FormData();
    formParams.append('image', file)
    formParams.append('id', '12');
    return this.http.post('http://localhost:8080/Company/upload', formParams)
  }


}
