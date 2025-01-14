package task5;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import task5.model.Account;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;
import task5.service.CurrencyExchangeTask;
import task5.service.ExchangeService;

public class CurrencyExchangeSimulator {
  public static void main(String[] args) {
    ExchangeService exchangeService = new ExchangeService();

    Account account = new Account("User", Map.of(
        CurrencyType.USD, BigDecimal.valueOf(1000),
        CurrencyType.EUR, BigDecimal.valueOf(500)
    ));

    ExchangeRate rate = new ExchangeRate(CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(0.85));

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      executorService.submit(new CurrencyExchangeTask(account, CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(100), rate, exchangeService));
    }

    executorService.shutdown();
  }
}
