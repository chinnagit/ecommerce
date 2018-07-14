import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class LoginService {
    constructor(
        private _router: Router, private _http: Http){}

    obtainAccessToken(loginData){
        console.log("In obtainAccessToken");
        let params = new URLSearchParams();
        params.append('username',loginData.username);
        params.append('password',loginData.password);
        params.append('grant_type','password');
        params.append('client_id','fooClientIdPassword');

        let headers = new Headers({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("fooClientIdPassword:secret")});
        let options = new RequestOptions({ headers: headers });
        console.log(params.toString());
        this._http.post('http://localhost:9081/security-oauth-server/oauth/token', params.toString(), options)
            .map(res => res.json())
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials')
            );
    }


    saveToken(token){
        var expireDate = new Date().getTime() + (100000 * token.expires_in);
        Cookie.set("access_token", token.access_token, expireDate);
        console.log('Obtained Access token');
        this._router.navigate(['/']);
    }

    checkCredentials(){
        if (Cookie.get('access_token') == 'undefined' || !Cookie.check('access_token')){
            this._router.navigate(['/login']);
        }

        // this._router.navigate(['/login']);
    }

    logout() {
        Cookie.delete('access_token');
        this._router.navigate(['/login']);
    }
}