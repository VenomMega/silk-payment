package kz.alibek.customer.controller;

import java.util.List;
import kz.alibek.customer.model.Client;
import kz.alibek.customer.model.dto.ClientDto;
import kz.alibek.customer.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class DefaultClientController implements ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> findAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Override
    public ResponseEntity<Client> updateById(Client entity, Long id) {
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClientBalanceById(@PathVariable Long id, @RequestBody BigDecimal sum) {
        return ResponseEntity.ok(clientService.updateClientBalanceById(id, sum));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
