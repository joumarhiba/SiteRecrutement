import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Offre } from '../offre/offre';
import { Company } from '../Company/company';
import { Admin } from '../admin/admin';


@Injectable({
    providedIn:'root'
})

export class OffreService {
    private apiServerUrl = 'http://localhost:8080';

constructor(private http: HttpClient) { }

public getOffres(): Observable<Offre[]>{
    return this.http.get<any>(`${this.apiServerUrl}/Offre/all`);
}

public getOffreDetails(offreId: number): Observable<Offre>{
    return this.http.get<any>(`${this.apiServerUrl}/Offre/find/${offreId}`);
}

public getValidatedOffres(): Observable<Offre[]>{
    return this.http.get<any>(`${this.apiServerUrl}/Offre/validatedOffres`);
}

public addOffre(offre: Offre): Observable<Offre>{
    return this.http.post<any>(`${this.apiServerUrl}/Offre/add`, offre);
}

public updateOffre(offre: Offre): Observable<Offre>{
    return this.http.put<any>(`${this.apiServerUrl}/Offre/update`, offre);
}

public deleteOffre(offreId: number): Observable<void>{
    return this.http.delete<any>(`${this.apiServerUrl}/Offre/delete/${offreId}`);
}


public getCompanies(): Observable<Company[]>{
    return this.http.get<any>(`${this.apiServerUrl}/Company/all`);
}


public addCompany(company: Company): Observable<Company>{
    return this.http.post<any>(`${this.apiServerUrl}/Company/add`, company);
}


public login( user: Company | Admin, userRole: string) : Observable<Company | Admin>{
    return this.http.post<any>(`${this.apiServerUrl}/auth/${userRole}`,user);
}

}