import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Offre } from '../offre/offre';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = 'http://localhost:8080';


  public getAllOffres(): Observable<Offre[]>{
    return this.http.get<any>(`${this.apiServerUrl}/Offre/all`);
}

  public updateOffreStatus(offre: Offre):Observable<Offre> {
      return this.http.put<any>(`${this.apiServerUrl}/Offre/updateStatus`,offre)
}
}
