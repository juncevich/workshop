package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {
  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress deliveryAddress;
  private final Money price;
  private final List<OrderItem> items;

  private TrackingId trackingId;
  private OrderStatus orderStatus;
  private List<String> failureMessages;

  private Order(OrderBuilder builder) {
    super.setId(builder.orderId);
    this.customerId = builder.customerId;
    this.restaurantId = builder.restaurantId;
    this.deliveryAddress = builder.deliveryAddress;
    this.price = builder.price;
    this.items = builder.items;
    this.trackingId = builder.trackingId;
    this.orderStatus = builder.orderStatus;
    this.failureMessages = builder.failureMessages;
  }

  public static OrderBuilder builder() {
    return new OrderBuilder();
  }

  public CustomerId getCustomerId() {
    return customerId;
  }

  public RestaurantId getRestaurantId() {
    return restaurantId;
  }

  public StreetAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public Money getPrice() {
    return price;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public TrackingId getTrackingId() {
    return trackingId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public List<String> getFailureMessages() {
    return failureMessages;
  }

  public static class OrderBuilder {
    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress deliveryAddress;
    private Money price;
    private List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    OrderBuilder() {
    }

    public OrderBuilder orderId(OrderId orderId) {
      this.orderId = orderId;
      return this;
    }

    public OrderBuilder customerId(CustomerId customerId) {
      this.customerId = customerId;
      return this;
    }

    public OrderBuilder restaurantId(RestaurantId restaurantId) {
      this.restaurantId = restaurantId;
      return this;
    }

    public OrderBuilder deliveryAddress(StreetAddress deliveryAddress) {
      this.deliveryAddress = deliveryAddress;
      return this;
    }

    public OrderBuilder price(Money price) {
      this.price = price;
      return this;
    }

    public OrderBuilder items(List<OrderItem> items) {
      this.items = items;
      return this;
    }

    public OrderBuilder trackingId(TrackingId trackingId) {
      this.trackingId = trackingId;
      return this;
    }

    public OrderBuilder orderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
      return this;
    }

    public OrderBuilder failureMessages(List<String> failureMessages) {
      this.failureMessages = failureMessages;
      return this;
    }

    public Order build() {
      return new Order(this);
    }

    public String toString() {
      return "Order.OrderBuilder(customerId=" + this.customerId + ", restaurantId=" + this.restaurantId +
             ", deliveryAddress=" + this.deliveryAddress + ", price=" + this.price + ", items=" + this.items +
             ", trackingId=" + this.trackingId + ", orderStatus=" + this.orderStatus + ", failureMessages=" +
             this.failureMessages + ")";
    }
  }
}
