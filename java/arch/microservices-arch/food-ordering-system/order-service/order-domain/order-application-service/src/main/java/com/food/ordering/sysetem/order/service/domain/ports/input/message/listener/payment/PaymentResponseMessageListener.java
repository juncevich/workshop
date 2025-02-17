package com.food.ordering.sysetem.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.sysetem.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
  void paymentCompleted(PaymentResponse paymentResponse);

  void paymentCancelled(PaymentResponse paymentResponse);
}
