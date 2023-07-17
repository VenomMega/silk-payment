package kz.alibek.transaction.listener;

import kz.alibek.payment.model.PaymentDto;
import kz.alibek.transaction.config.RabbitConfig;
import kz.alibek.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMessageListener {

  private Logger log = LogManager.getLogger();

  private final TransactionService transactionService;

  @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
  public void handlePaymentDto(PaymentDto paymentDto) {
    log.info("[TRANSACTION] Storing transaction for {}", paymentDto);

    transactionService.createTransaction(paymentDto);
  }
}
