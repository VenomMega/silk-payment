package kz.alibek.customer.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private String firstName;
    private String lastName;
    private BigDecimal balance;
}
