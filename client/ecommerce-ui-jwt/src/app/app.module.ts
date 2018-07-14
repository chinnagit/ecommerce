import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './commerce/home.component';
import { MainComponent } from './commerce/main.component';
import { ElectronicsComponent } from './commerce/electronics.component';
import {ClothingComponent} from './commerce/clothing.component';
import {ShoesComponent} from './commerce/shoes.component';
import {CartComponent} from './commerce/cart.component';
//import { FooComponent } from './commerce/foo.component';
import { ClarityModule } from '@clr/angular';
import { SESSION_STORAGE, StorageServiceModule, WebStorageService} from 'angular-webstorage-service';
import {CART_SERVICE_STORAGE, CartService} from './commerce/cart.service';
import {CheckOutComponent} from './commerce/checkout.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    // FooComponent,
    MainComponent,
    ElectronicsComponent,
    ClothingComponent,
    ShoesComponent,
    CartComponent,
    CheckOutComponent
  ],
  imports: [
    BrowserModule,
    StorageServiceModule,
    FormsModule,
    HttpModule,
      ClarityModule,
    RouterModule.forRoot([

      { path: 'home', component: HomeComponent },
      {
        path: '',
        component: HomeComponent,
          children: [
              {
                  path: '', redirectTo: 'main', pathMatch: 'full'
              },
              {
                  path: 'main',
                  component: MainComponent,
                  children: [
                      {
                          path: '', redirectTo: 'electronics', pathMatch: 'full'
                      },
                      {
                          path: 'electronics',
                          component: ElectronicsComponent
                      },
                      {
                          path: 'clothing',
                          component: ClothingComponent
                      },
                      {
                          path: 'shoes',
                          component: ShoesComponent
                      },
                      {
                          path: 'cart',
                          component: CartComponent
                      },
                      {
                          path: 'checkout',
                          component: CheckOutComponent
                      }
                  ]
              },
          ]
      },
      { path: 'login', component: LoginComponent }])
  ],
  providers: [{ provide: CART_SERVICE_STORAGE, useExisting: SESSION_STORAGE },
      CartService],
  bootstrap: [AppComponent]
})
export class AppModule { }
