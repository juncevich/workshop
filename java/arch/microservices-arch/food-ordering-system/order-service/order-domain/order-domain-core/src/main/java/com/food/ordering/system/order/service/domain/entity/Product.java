package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Getter
public class Product extends BaseEntity<ProductId> {
  private final String name;
  private final Money price;

  private Product(ProductId productId, String name, Money price) {
    super.setId(productId);
    this.name = name;
    this.price = price;
  }
}
