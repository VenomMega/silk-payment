package kz.alibek.payment.controller;

import kz.alibek.payment.model.Payment;
import kz.alibek.payment.model.PaymentDto;
import kz.alibek.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping
  public ResponseEntity<Void> createEntity(@RequestBody PaymentDto entity) {
    paymentService.sendPaymentDto(entity);
    return null;
  }
}
