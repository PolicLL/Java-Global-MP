package task5;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import task5.model.Account;
import task5.model.Currency;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;
import task5.service.CurrencyExchangeTask;
import task5.service.ExchangeService;

public class CurrencyExchangeSimulator {
  public static void main(String[] args) {
    // Initialize services
    ExchangeService exchangeService = new ExchangeService();

    // Sample account and currencies
    Account account = new Account("user1", Map.of(
        CurrencyType.USD, BigDecimal.valueOf(1000),
        CurrencyType.EUR, BigDecimal.valueOf(500)
    ));

    // Sample exchange rate (1 USD = 0.85 EUR)
    ExchangeRate rate = new ExchangeRate(CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(0.85));

    // Use ExecutorService to simulate concurrent operations
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      executorService.submit(new CurrencyExchangeTask(account, CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(100), rate, exchangeService));
    }

    executorService.shutdown();
  }
}
