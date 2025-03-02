package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
  private OrderId orderId;
  private final Product product;
  private final int quantity;
  private final Money price;
  private final Money subTotal;

  @Builder
  public OrderItem(OrderItemId orderItemId, Product product, int quantity, Money price, Money subTotal) {
    super.setId(orderItemId);
    this.product = product;
    this.quantity = quantity;
    this.price = price;
    this.subTotal = subTotal;
  }

  void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
    this.orderId = orderId;
    super.setId(orderItemId);
  }

  boolean isPriceValid() {
    return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.multiply(quantity).equals(subTotal);
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OrderItem orderItem)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return getQuantity() == orderItem.getQuantity() && Objects.equals(getOrderId(), orderItem.getOrderId()) &&
           Objects.equals(getProduct(), orderItem.getProduct()) &&
           Objects.equals(getPrice(), orderItem.getPrice()) &&
           Objects.equals(getSubTotal(), orderItem.getSubTotal());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getOrderId(), getProduct(), getQuantity(), getPrice(), getSubTotal());
  }
}
