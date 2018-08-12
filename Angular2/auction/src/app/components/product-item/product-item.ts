import {Component, Input} from '@angular/core';
import {Product} from '../../services/product-service';

@Component({
  selector: 'auction-product-item',
  templateUrl: 'product-item.html'
})
export default class ProductItemComponent {
  @Input() product: Product;
}
