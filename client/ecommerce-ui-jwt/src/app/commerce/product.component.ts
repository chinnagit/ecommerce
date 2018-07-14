import {Component} from '@angular/core';

@Component({
})
export class ProductComponent {
    name: string;
    price: number ;
    description: string ;
    constructor( name: string, description: string, price: number){
        this.name = name;
        this.description = description;
        this.price = price;
    }
}