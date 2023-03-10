import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Offre } from '../offre/offre';
import { Company } from '../Company/company';


@Injectable({
    providedIn:'root'
})

export class HomeService {
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






// calling to server to generate the token
generateCompanyToken(credentials: any){
    //token generate
    return this.http.post(`${this.apiServerUrl}/auth/authenticateCompany`,credentials)
}

//for login company
loginCompany(token: any) {
    localStorage.setItem("tokenCompany",token);
    return true;
}

// to check if the user is logged in or not
isLoggedIn(){
    let tokenCompany = localStorage.getItem("tokenCompany");
    if(tokenCompany == null || tokenCompany== '' || tokenCompany == undefined) {
        return false;
    }
    else {
        return true;
    }
}

getToken(){
    return localStorage.getItem("tokenCompany")
}

logout(){
    localStorage.removeItem("tokenAdmin");
    localStorage.removeItem("tokenCompany");
    return true
}
// ----------------Admin---------------------------------


// calling to server to generate the token for admin
generateAdminToken(aCredentials: any){
    //token generate
    return this.http.post(`${this.apiServerUrl}/auth/authenticateAdmin`,aCredentials)
}

//for loginadmin
loginAdmin(token: any) {
    localStorage.setItem("tokenAdmin",token);
    return true;
}

// to check if the user is logged in or not


getTokenAdmin(){
    return localStorage.getItem("tokenAdmin")
}
// to check if the user is logged in or not
isAdminLoggedIn(){
    let tokenAdmin = localStorage.getItem("tokenAdmin")
    if(tokenAdmin == null || tokenAdmin == '' || tokenAdmin== undefined) {
        return false;
    }
    else {
        return true;
    }
}


onSearch(request: any, search: string){
    const params = request;
    const profil = search;
    return this.http.get<any>(`${this.apiServerUrl}/Offre/`+profil, {params})
}

}