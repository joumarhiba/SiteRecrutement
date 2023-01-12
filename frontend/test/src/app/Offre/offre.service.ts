import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Offre } from './offre';

@Injectable({
  providedIn: 'root'
})
export class OffreService {
  public apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public addOffre(offre: Offre): Observable<Offre>{
    return this.http.post<any>(`${this.apiServerUrl}/Offre/add`, offre);
}

public updateOffre(offre: Offre): Observable<Offre> {
  return this.http.put<any>(`${this.apiServerUrl}/Offre/update`, offre);
}
}
