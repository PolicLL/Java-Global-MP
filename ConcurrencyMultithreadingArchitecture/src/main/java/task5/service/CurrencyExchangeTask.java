package task5.service;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import task5.exception.InsufficientFundsException;
import task5.model.Account;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;

@AllArgsConstructor
public class CurrencyExchangeTask implements Runnable {
  private Account account;
  private final CurrencyType fromCurrencyCode;
  private final CurrencyType toCurrencyCode;
  private final BigDecimal amount;
  private final ExchangeRate rate;
  private final ExchangeService exchangeService;

  @Override
  public void run() {
    try {
      account = exchangeService.exchangeCurrency(account, fromCurrencyCode, toCurrencyCode, amount, rate);
      System.out.println("Exchange completed successfully for " + amount +
          " " + fromCurrencyCode + " to " + toCurrencyCode);
    } catch (InsufficientFundsException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
