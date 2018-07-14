import {Component} from '@angular/core';
import {Product} from '../generated/productsapi/model/Product';
import {CartService} from "./cart.service";

@Component({
    selector: 'app-shoes',
    templateUrl: './shoes.component.html'
})
export class ShoesComponent {
    public shoes: Product[] = [];
    constructor(private cartService: CartService){
        let prod1: Product = {};
        prod1.name = 'Adidas';
        prod1.description = 'boys shoe with size 2`';
        prod1.price = 45.99;

        let prod2: Product = {};
        prod2.name = 'Nike';
        prod2.description = 'Men shoe with size 12`';
        prod2.price = 125.99;

        let prod3: Product = {};
        prod3.name = 'Rebok';
        prod3.description = 'Women shoe with size 8`';
        prod3.price = 100.99;

        let prod4: Product = {};
        prod4.name = 'Under Armour';
        prod4.description = 'boys shoe with size 2`';
        prod4.price = 55.99;

        let prod5: Product = {};
        prod5.name = 'Nike';
        prod5.description = 'Men shoe with size 12`';
        prod5.price = 155.99;

        let prod6: Product = {};
        prod6.name = 'Nike';
        prod6.description = 'Women shoe with size 8`';
        prod6.price = 110.99;

        this.shoes.push(prod1);
        this.shoes.push(prod2);
        this.shoes.push(prod3);
        this.shoes.push(prod4);
        this.shoes.push(prod5);
        this.shoes.push(prod6);
        // this.shoes.push(new Product('Adidas', 'boys shoe with size 2`', 45.99));
        // this.shoes.push(new Product('Nike', 'Men shoe with size 12`', 125.59));
        // this.shoes.push(new Product('Rebok', 'Women shoe with size 8`', 100.99));
        //
        // this.shoes.push(new Product('Under Armour', 'boys shoe with size 2`', 55.99));
        // this.shoes.push(new Product('Nike', 'Men shoe with size 12`', 155.59));
        // this.shoes.push(new Product('Rebok', 'Women shoe with size 8`', 110.99));
    }
    ngInit(){

    }

    addToCart(index){
        this.cartService.save(this.shoes[index]);
    }


}
