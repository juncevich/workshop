package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Customer extends AggregateRoot<CustomerId> {

  public Customer(CustomerId customerId) {
    super.setId(customerId);
  }
}
