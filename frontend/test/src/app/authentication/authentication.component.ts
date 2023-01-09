import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit{

 authRequest : any = {
    "email":'entreprise1@gmail.com',
    "password": '12345678'
  }
  result: any;

  constructor(private authService: AuthenticationService) {}

ngOnInit(): void {
    this.getAccessToken(this.authRequest);
}


  public getAccessToken(authRequest: any) {
    let response =this.authService.generateToken(this.authRequest);
    response.subscribe((data : any )=>
    {
      this.welcome(data);
      console.log(data);

    }
    )
  }

  public welcome(token: string){
    let response =this.authService.welcome(token);
    response.subscribe((data: any) => this.result =data );
  }

}
