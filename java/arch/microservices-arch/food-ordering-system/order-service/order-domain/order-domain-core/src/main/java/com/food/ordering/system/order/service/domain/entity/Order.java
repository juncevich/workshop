package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

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


  public void initializeOrder() {
    setId(new OrderId(UUID.randomUUID()));
    trackingId = new TrackingId(UUID.randomUUID());
    orderStatus = OrderStatus.PENDING;
    initializeOrderItems();
  }

  private void initializeOrderItems() {
    long itemId = 1;
    for (OrderItem orderItem : items) {
      orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
    }
  }

  public void validateOrder() {
    validateInitialOrder();
    validateTotalPrice();
    validateItemsPrice();
  }

  private void validateItemsPrice() {
    Money orderItemsTotal = items.stream().map(orderItem -> {
      validateItemPrice(orderItem);
      return orderItem.getSubTotal();
    }).reduce(Money.ZERO, Money::add);

    if (!price.equals(orderItemsTotal)) {
      throw new OrderDomainException(
          "Total price: " + price.getAmount() + "is not equal to Order items total: " + orderItemsTotal.getAmount() +
          "!");
    }
  }

  private void validateItemPrice(OrderItem orderItem) {
    if (!orderItem.isPriceValid()) {
      throw new OrderDomainException(
          "Order item price: " + orderItem.getPrice().getAmount() + " is not valid for product " +
          orderItem.getProduct().getId().getValue());
    }
  }

  private void validateTotalPrice() {
    if (price == null || !price.isGreaterThanZero()) {
      throw new OrderDomainException("Total price must be greater than zero");
    }
  }

  private void validateInitialOrder() {
    if (orderStatus != null || getId() != null) {
      throw new OrderDomainException("Order is not int correct state for initialization!");
    }
  }


  public void pay() {
    if (orderStatus != OrderStatus.PENDING) {
      throw new OrderDomainException("Order is not in correct state for pay operation!");
    }
    orderStatus = OrderStatus.PAID;
  }

  public void approve() {
    if (orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for approve operation!");
    }
    orderStatus = OrderStatus.APPROVED;
  }

  public void initCancel(List<String> failureMessages) {
    if (orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for initCancel operation!");
    }
    orderStatus = OrderStatus.CANCELLING;
    updateFailureMessages(failureMessages);
  }

  public void cancel(List<String> failureMessages) {
    if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
      throw new OrderDomainException("Order id not in correct state for cancel operation!");
    }
    orderStatus = OrderStatus.CANCELLED;
    updateFailureMessages(failureMessages);
  }

  private void updateFailureMessages(List<String> failureMessages) {
    if (this.failureMessages != null && failureMessages != null) {
      this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
    }
    if (this.failureMessages == null) {
      this.failureMessages = failureMessages;
    }
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
