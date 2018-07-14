import { Component } from '@angular/core';
import {AppService, Foo} from './../app.service'
import { Cookie } from 'ng2-cookies';

@Component({
	selector: 'main-details',
	providers: [AppService],
	templateUrl: "./main.component.html",
	styleUrls: ['./layout.demo.scss']
})

export class MainComponent {
    public foo = new Foo(1,'sample foo');
	/*
	/*
	* gateway-oauth-resourcee is API gateway server, and it can access the subservices with the token
	*/
    private foosUrl = 'http://localhost:8082/gateway-oauth-resource/foos/';  
	
    private productsUrl = 'http://localhost:8082/gateway-oauth-resource/productservice/products'; 
 
	private customerUrl = 'http://localhost:8082/gateway-oauth-resource/userservice/user';
	

    constructor(private _service:AppService) {}
	
	getFoo(){
		//this.getProducts();
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
