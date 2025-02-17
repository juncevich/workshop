package com.food.ordering.sysetem.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {

}
