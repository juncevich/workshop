package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import lombok.Builder;
import lombok.Getter;

//@RequiredArgsConstructor
@Getter
public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

  @Builder
  public Product(ProductId productId, String name, Money price) {
    super.setId(productId);
    this.name = name;
    this.price = price;
  }

  public Product(ProductId productId) {
    super.setId(productId);
  }

  public void updateWithConfirmedNameAndPrice(String name, Money price) {
    this.name = name;
    this.price = price;
  }
}
