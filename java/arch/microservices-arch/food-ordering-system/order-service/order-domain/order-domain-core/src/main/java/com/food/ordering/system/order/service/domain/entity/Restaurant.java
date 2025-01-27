package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;



@Getter
@Builder
public class Restaurant extends AggregateRoot<RestaurantId> {
  private final List<Product> products;
  private boolean active;

  public Restaurant(RestaurantId id, List<Product> products, boolean active) {
    super.setId(id);
    this.products = products;
    this.active = active;
  }
}
