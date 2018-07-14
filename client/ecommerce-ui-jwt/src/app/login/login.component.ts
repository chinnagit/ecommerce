import { Component } from '@angular/core';
import {LoginService} from './login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'login-form',
  providers: [LoginService],
  template: `
    <div class="col-sm-6">
        <h1>Login</h1>
        <div class="col-sm-12 form-group">
             <label>Username</label>
             <input class="form-control" type="text" [(ngModel)]="loginData.username" />
        </div>
        <div class="col-sm-12 form-group">
            <label>Password</label>
            <input class="form-control" type="password"  [(ngModel)]="loginData.password"/>
        </div>
        <div class="col-sm-12">
            <button class="btn btn-primary pull-right" (click)="login()" type="submit">Login</button>
        </div>
    </div>`
})
export class LoginComponent {
    public loginData = {username: "", password: ""};

    constructor(private _router: Router, private _service:LoginService) {}
 
    login() {
        console.log("In login component");
        //this._router.navigate(['/home']);
         this._service.obtainAccessToken(this.loginData);
    }
}
