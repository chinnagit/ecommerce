import { Component } from '@angular/core';
import {AppService, Foo} from './app.service'
import { Cookie } from 'ng2-cookies';

@Component({
  selector: 'foo-details',
  providers: [AppService],  
  template: `<div class="container">
    <h1 class="col-sm-12">Foo Details</h1>
    <div class="col-sm-12">
        <label class="col-sm-3">ID</label> <span>{{foo.id}}</span>
    </div>
    <div class="col-sm-12">
        <label class="col-sm-3">Name</label> <span>{{foo.name}}</span>
    </div>
    <div class="col-sm-12">
        <button class="btn btn-primary" (click)="getFoo()" type="submit">New Foo</button>        
    </div>
</div>`
})

export class FooComponent {
    public foo = new Foo(1,'sample foo');
	
	/*
	* gateway-oauth-resourcee is API gateway server, and it can access the subservices with the token
	*/
    private foosUrl = 'http://localhost:8082/gateway-oauth-resource/foos/';  
	
    private productsUrl = 'http://localhost:8082/gateway-oauth-resource/productservice/products'; 
 
	private customerUrl = 'http://localhost:8082/gateway-oauth-resource/userservice/user';
	

    constructor(private _service:AppService) {}
	
	getFoo(){
		this.getProducts();
		this.getFoo1();
		//this.getCustomers();
		
	}
	
	getProducts(){
		console.log("getting products data ");
        this._service.getResource(this.productsUrl)
         .subscribe(
                     data => { console.log("data from gateway server for products: "+data[0].name);},
		error =>  {this.foo.name = 'products Error';  });
	}
	
	getCustomers(){
		console.log("getting customers data ");
        this._service.getResource(this.customerUrl)
         .subscribe(
                     data => { console.log("data from gateway server for customers: "+data[0].name);},
		error =>  {console.log(error); this.foo.name = 'customer Error';  });
	}
		

    getFoo1(){
		console.log("getting foos data "+this.foo.id);
        this._service.getResource(this.foosUrl+this.foo.id)
		//this._service.getResource(this.foosUrl)
         .subscribe(
                     data => this.foo = data,
		error =>  {this.foo.name = 'Error';  });
    }
	
}
