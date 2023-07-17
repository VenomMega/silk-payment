package kz.alibek.payment.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDto {
  @NotNull(message = "Sender ID cannot be null")
  private Long senderId;

  @NotNull(message = "Transaction sum cannot be null")
  private BigDecimal transactionSum;

  @NotNull(message = "Receiver ID cannot be null")
  private Long receiverId;

  @NotNull(message = "Currency cannot be null")
  private String currency;
}
