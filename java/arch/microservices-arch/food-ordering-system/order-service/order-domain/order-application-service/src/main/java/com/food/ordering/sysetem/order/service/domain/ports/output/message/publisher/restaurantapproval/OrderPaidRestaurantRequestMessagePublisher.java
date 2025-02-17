package com.food.ordering.sysetem.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
