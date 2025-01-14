package task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task5.exception.InsufficientFundsException;
import task5.model.Account;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;

import java.math.BigDecimal;
import java.util.Map;
import task5.service.ExchangeService;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeServiceTest {
  private ExchangeService exchangeService;
  private Account account;
  private ExchangeRate exchangeRate;

  @BeforeEach
  void setUp() {
    exchangeService = new ExchangeService();

    account = new Account("User", Map.of(
        CurrencyType.USD, BigDecimal.valueOf(1000),
        CurrencyType.EUR, BigDecimal.valueOf(500)
    ));

    exchangeRate = new ExchangeRate(CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(0.85));
  }

  @Test
  void testExchangeCurrencySuccessful() throws InsufficientFundsException {
    Account updatedAccount = exchangeService.exchangeCurrency(account, CurrencyType.USD,
        CurrencyType.EUR, BigDecimal.valueOf(100), exchangeRate);

    assertEquals(BigDecimal.valueOf(900), updatedAccount.wallet().get(CurrencyType.USD));
    assertEquals(new BigDecimal("585.00"), updatedAccount.wallet().get(CurrencyType.EUR));
  }

  @Test
  void testExchangeCurrencyInsufficientFunds() {
    InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
      exchangeService.exchangeCurrency(account, CurrencyType.USD, CurrencyType.EUR, BigDecimal.valueOf(2000), exchangeRate);
    });

    assertEquals("Insufficient funds in USD", exception.getMessage());
  }

  @Test
  void testExchangeCurrencyNoAmountInToCurrency() throws InsufficientFundsException {

    Account newAccount = new Account("User2", Map.of(CurrencyType.USD, BigDecimal.valueOf(1000)));

    Account updatedAccount = exchangeService.exchangeCurrency(newAccount, CurrencyType.USD,
        CurrencyType.EUR, BigDecimal.valueOf(100), exchangeRate);

    assertEquals(new BigDecimal("85.00"), updatedAccount.wallet().get(CurrencyType.EUR));
    assertEquals(BigDecimal.valueOf(900), updatedAccount.wallet().get(CurrencyType.USD));
  }

  @Test
  void testExchangeCurrencyExactAmount() throws InsufficientFundsException {

    Account updatedAccount = exchangeService.exchangeCurrency(account, CurrencyType.USD,
        CurrencyType.EUR, BigDecimal.valueOf(1000), exchangeRate);

    assertEquals(BigDecimal.ZERO, updatedAccount.wallet().get(CurrencyType.USD));
    assertEquals(new BigDecimal("1350.00"), updatedAccount.wallet().get(CurrencyType.EUR));
  }
}
