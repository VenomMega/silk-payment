package kz.alibek.customer.service;

import kz.alibek.core.IBANGenerator;
import kz.alibek.customer.exception.ClientException;
import kz.alibek.core.ExceptionMessage;
import kz.alibek.customer.model.Client;
import kz.alibek.customer.model.dto.ClientDto;
import kz.alibek.customer.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private Logger log = LogManager.getLogger();
    private final ClientRepository clientRepository;


    public void createClient(ClientDto clientDto) {
        Client client = Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .balance(clientDto.getBalance())
                .accountNumber(IBANGenerator.generateIBAN())
                .currency("KZT")
                .build();

        log.info("[CLIENT] Starting process createClient {}", client);
        clientRepository.save(client);
        log.info("[CLIENT] Process createClient executed");
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException(ExceptionMessage.CLIENT_NOT_FOUND.getMessage() + id));
    }

    public Client updateClientBalanceById(Long id, BigDecimal sum) {
        Client client = getClientById(id);
        client.setBalance(sum);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        log.info("[CLIENT] deleteClient process started for ID = {} ", id);
        Client client = getClientById(id);
        log.info("[CLIENT] getClientById with ID = {}. {}", id, client);
        clientRepository.delete(client);
        log.info("[CLIENT] deleteClient process finished");
    }
}
