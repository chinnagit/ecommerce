import {Component} from '@angular/core';
import {ProductComponent} from "./product.component";
import {CartService} from "./cart.service";

@Component({
    selector: 'app-cart',
    // providers: [CartService],
    templateUrl: "cart.component.html"
})
export class CartComponent {
    cartItems: ProductComponent[] = [];
    constructor(private cartService: CartService){
        this.cartItems = this.cartService.getCart();
    }

    ngInit(){
        this.cartItems = this.cartService.getCart();
    }
    getCartItems(){
        return this.cartItems;
    }
}
