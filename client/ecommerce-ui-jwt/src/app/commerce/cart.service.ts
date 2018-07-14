import {Injectable, Inject, InjectionToken} from '@angular/core';
import {ProductComponent} from "./product.component";
import {SESSION_STORAGE, StorageService } from 'angular-webstorage-service';
import {Product} from '../generated/productsapi/model/Product';

const STORAGE_KEY = 'cartdata';
export const CART_SERVICE_STORAGE =
    new InjectionToken<StorageService>('CART_SERVICE_STORAGE');

@Injectable()
export class CartService {
    public cartItems: Product[] = [];

    constructor(@Inject(CART_SERVICE_STORAGE) private storage: StorageService) {
        this.storage.set(STORAGE_KEY, this.cartItems);
    }
    save(item: Product){
        // this.cartItems.push(item);
        let cartItemsLocal = this.storage.get(STORAGE_KEY);
        console.log('saving item to cart, current length: '+cartItemsLocal.length);
        cartItemsLocal.push(item);

        this.storage.set("cartdata", cartItemsLocal);
        console.log('after save cart items length: '+this.storage.get(STORAGE_KEY).length);
        // this.getCart();
        // console.log('saving item to cart');
        //
        // console.log('after save cart items length: '+this.cartItems.length);
    }

    getCart(){
        let cartItemsLocal = this.storage.get("cartdata");
        console.log('CartService- While Fetch cart items length: '+cartItemsLocal.length);
        for(let data of cartItemsLocal){
            console.log(data.name);
        }
        return cartItemsLocal;
    }
    // public cartItems: ProductComponent[] = [];
    //
    // constructor(@Inject(CART_SERVICE_STORAGE) private storage: StorageService) {
    //     this.storage.set(STORAGE_KEY, this.cartItems);
    // }
    // save(item: ProductComponent){
    //     // this.cartItems.push(item);
    //     let cartItemsLocal = this.storage.get(STORAGE_KEY);
    //     console.log('saving item to cart, current length: '+cartItemsLocal.length);
    //     cartItemsLocal.push(item);
    //
    //     this.storage.set("cartdata", cartItemsLocal);
    //     console.log('after save cart items length: '+this.storage.get(STORAGE_KEY).length);
    //     // this.getCart();
    //     // console.log('saving item to cart');
    //     //
    //     // console.log('after save cart items length: '+this.cartItems.length);
    // }
    //
    // getCart(){
    //     let cartItemsLocal = this.storage.get("cartdata");
    //     console.log('CartService- While Fetch cart items length: '+cartItemsLocal.length);
    //     for(let data of cartItemsLocal){
    //         console.log(data.name);
    //     }
    //     return cartItemsLocal;
    // }
}