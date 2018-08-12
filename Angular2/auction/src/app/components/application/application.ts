import {Component, ViewEncapsulation} from '@angular/core';
import {Product, ProductService} from '../../services/product-service';

@Component({
  selector: 'auction-application', // <1>
  templateUrl: 'application.html', // <3>
  styleUrls: ['application.css'], // <4>
  encapsulation:ViewEncapsulation.None
})

export default class ApplicationComponent {
  products: Array<Product> = []; // <1>

  constructor(private productService: ProductService) { // <2>
    this.products = this.productService.getProducts(); // <3>
  }
}



