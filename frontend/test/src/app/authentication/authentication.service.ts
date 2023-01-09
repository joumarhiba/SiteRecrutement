import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }


  public generateToken(request: any) {
    return this.http.post("http://localhost:8080/auth/authenticateCompany", request, {responseType: 'text' as 'json'});
  }

  public welcome(token: any) {
    let tokenStr = token
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.http.get("http://localhost:8080/auth/", {headers, responseType: 'text' as 'json'});
  }

}
