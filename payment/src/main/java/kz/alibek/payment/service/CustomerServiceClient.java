package kz.alibek.payment.service;

import java.math.BigDecimal;
import kz.alibek.customer.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="customer-service", url="http://localhost:8080")
public interface CustomerServiceClient {
  @GetMapping("/clients/{id}")
  Client getClient(@PathVariable("id") Long id);

  @PutMapping("/clients/update/{id}")
  Client updateClientBalance(@PathVariable("id") Long id, @RequestBody BigDecimal sum);
}
