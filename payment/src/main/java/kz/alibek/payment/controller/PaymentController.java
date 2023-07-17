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

  public ResponseEntity<Payment> getById(Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<Payment> createEntity(@RequestBody PaymentDto entity) {
    paymentService.sendPaymentDto(entity);
    System.out.println(entity.getTransactionSum());
    System.out.println("OK");
    return null;
  }

  public ResponseEntity<Payment> makePayment(Long id, Payment entity) {
    return null;
  }

  protected void deleteEntity(Long id) {

  }
}
