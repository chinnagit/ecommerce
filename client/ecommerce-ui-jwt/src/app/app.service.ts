import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
 
export class Foo {
  constructor(
    public id: number,
    public name: string) { }
} 

@Injectable()
export class AppService {
  constructor(
    private _router: Router, private _http: Http){}

  getResource(resourceUrl) : Observable<Foo>{
	console.log("access_token: \n"+Cookie.get('access_token'));
    var headers = new Headers({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+Cookie.get('access_token')});
    var options = new RequestOptions({ headers: headers });
	
	/*this._http.get(resourceUrl, options)
    .map(res => res.json())
    .subscribe(
      data => this.saveToken(data),
      err => alert(err)
    ); */
	
    return this._http.get(resourceUrl, options)
                   .map((res:Response) => res.json())
                   .catch((error:any) => this.handleError(error));
  }
  
  private handleError(error: any) { 
	  let errMsg = (error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : 'Server error';
	  console.log(errMsg);
	  return Observable.throw(error);
	}
}