import {Component} from '@angular/core';
import {ProductsApi} from '../generated/productsapi/api/ProductsApi';
import {Product} from '../generated/productsapi/model/Product';
import {CartService} from "./cart.service";

@Component({
    selector: 'app-clothing',
    providers: [ProductsApi],
    templateUrl: 'clothing.component.html'
})
export class ClothingComponent {
    products: Product[] = [];
    constructor(private _productsApi:ProductsApi, private cartService: CartService){
        this.getProducts()
            .subscribe(
                data => {this.products = data; console.log("[ClothingComponent] data from gateway server for products: "+this.products[0].name);},
                error =>  {console.log('[ClothingComponent] products Error '+error); });
    }
    ngInit(){

    }

    addToCart(index){
        this.cartService.save(this.products[index]);
    }

    getProducts() {
        return this._productsApi.findAll();
    }
}
