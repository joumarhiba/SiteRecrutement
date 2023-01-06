import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Company } from './company';
import { Offre } from '../offre/offre';


@Injectable({
    providedIn:'root'
})

export class CompanyService {
    private apiServerUrl = 'http://localhost:8080';

constructor(private http: HttpClient) { }

public getCompanies(): Observable<Company[]>{
    return this.http.get<any>(`${this.apiServerUrl}/Company/all`);
}


public addCompany(company: Company): Observable<Company>{
    return this.http.post<any>(`${this.apiServerUrl}/Company/add`, company);
}

public findOffreByCompany(company: Company): Observable<Offre[]> {
    return this.http.post<any>(`${this.apiServerUrl}/Offre/companyOffres`,company);
}


}