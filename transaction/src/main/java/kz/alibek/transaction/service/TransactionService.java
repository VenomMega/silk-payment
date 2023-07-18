package kz.alibek.transaction.service;

import kz.alibek.core.ExceptionMessage;
import kz.alibek.customer.model.Client;
import kz.alibek.payment.model.PaymentDto;
import kz.alibek.payment.service.CustomerServiceClient;
import kz.alibek.transaction.exception.TransactionException;
import kz.alibek.transaction.model.Transaction;
import kz.alibek.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerServiceClient customerServiceClient;

    public void createTransaction(PaymentDto paymentDto) {

        Client sender = getClient(paymentDto.getSenderId());
        Client receiver = getClient(paymentDto.getReceiverId());

        Transaction transaction = Transaction.builder()
            .senderAccountNumber(sender.getAccountNumber())
            .receiverAccountNumber(receiver.getAccountNumber())
            .transactionSum(paymentDto.getTransactionSum())
            .build();

        customerServiceClient.updateClientBalance(sender.getId(), sender.getBalance().subtract(paymentDto.getTransactionSum()));
        customerServiceClient.updateClientBalance(receiver.getId(), receiver.getBalance().add(paymentDto.getTransactionSum()));

        transactionRepository.save(transaction);
    }

    private Client getClient(long id) {
        return customerServiceClient.getClient(id);
    }
}
