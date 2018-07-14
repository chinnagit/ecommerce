import {Component} from '@angular/core';
import {LoginService} from '../login/login.service'
 
@Component({
    selector: 'home-header',
    providers: [LoginService],
  template: `<div class="main-container" >
      <header class="header header-6">
          <div class="branding" style="width: 84%; float:left">
              <a href="#">
                  <!--<clr-icon shape="vm-bug"></clr-icon>-->
                  <span class="title">Welcome to My Ecommerce Store</span>
              </a>
          </div>
          <div class="header-nav" style="width: 8%; float:right">
              <a class="btn btn-default pull-right"(click)="logout()" href="#">Logout</a>
          </div>
          <div class="header-nav" style="width: 8%; float:right">
              <a class="btn btn-default pull-right" [routerLink]=" ['/main/cart']">Cart</a>
          </div>
      </header>
      <router-outlet></router-outlet>

      <!--<main-details></main-details>-->
</div>`
})
//<!--<foo-details></foo-details>-->
export class HomeComponent {
 
    constructor(
        private _service:LoginService){}
 
    ngOnInit(){
        this._service.checkCredentials();
    }
 
    logout() {
        this._service.logout();
    }
}