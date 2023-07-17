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
        Transaction transaction = new Transaction();

        Client sender = getClient(paymentDto.getSenderId());
        Client receiver = getClient(paymentDto.getReceiverId());

        transaction.setSenderAccountNumber(sender.getAccountNumber());
        transaction.setRecieverAccountNumber(receiver.getAccountNumber());

        if (sender.getBalance().compareTo(paymentDto.getTransactionSum()) >= 0){
            customerServiceClient.updateClientBalance(sender.getId(), sender.getBalance().subtract(paymentDto.getTransactionSum()));
            customerServiceClient.updateClientBalance(receiver.getId(), receiver.getBalance().add(paymentDto.getTransactionSum()));

            transactionRepository.save(transaction);
        }
        else throw new TransactionException(ExceptionMessage.NOT_ENOUGH_BALANCE_ON_ACCOUNT.getMessage());
    }

    private Client getClient(long id) {
        return customerServiceClient.getClient(id);
    }
}
