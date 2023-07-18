package kz.alibek.payment.service;

import kz.alibek.core.ExceptionMessage;
import kz.alibek.customer.model.Client;
import kz.alibek.payment.config.RabbitConfig;
import kz.alibek.payment.exception.PaymentException;
import kz.alibek.payment.model.Payment;
import kz.alibek.payment.model.PaymentDto;
import kz.alibek.payment.repository.PaymentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private Logger log = LogManager.getLogger();

    private final AmqpTemplate amqpTemplate;
    private final CustomerServiceClient customerServiceClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(AmqpTemplate amqpTemplate,
        CustomerServiceClient customerServiceClient,
        PaymentRepository paymentRepository) {
        this.amqpTemplate = amqpTemplate;
        this.customerServiceClient = customerServiceClient;
        this.paymentRepository = paymentRepository;
    }

    public void sendPaymentDto(PaymentDto paymentDto) {
        Client sender = customerServiceClient.getClient(paymentDto.getSenderId());
        Client receiver = customerServiceClient.getClient(paymentDto.getReceiverId());

        if (sender.getBalance().compareTo(paymentDto.getTransactionSum()) >= 0) {
            Payment payment = Payment.builder()
                .senderId(paymentDto.getSenderId())
                .receiverId(paymentDto.getReceiverId())
                .transactionSum(paymentDto.getTransactionSum())
                .build();

            paymentRepository.save(payment);

            log.info("[PAYMENT] Send payment {} from {} to {}", paymentDto, sender.getAccountNumber(), receiver.getAccountNumber());
            amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, paymentDto);
        }
        else throw new PaymentException(ExceptionMessage.NOT_ENOUGH_BALANCE_ON_ACCOUNT.getMessage());
    }
}
