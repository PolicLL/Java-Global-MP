package task5.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import task5.exception.InsufficientFundsException;
import task5.model.Account;
import task5.model.Currency;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;

public class ExchangeService {
  private static final Logger logger = Logger.getLogger(ExchangeService.class.getName());

  public Account exchangeCurrency(Account account, CurrencyType fromCurrencyCode,
      CurrencyType toCurrencyCode, BigDecimal amount, ExchangeRate rate) throws InsufficientFundsException {
    // Get the currencies
    Currency fromCurrency = account.currencies().get(fromCurrencyCode);
    if (fromCurrency == null || fromCurrency.amount().compareTo(amount) < 0) {
      throw new InsufficientFundsException("Insufficient funds in " + fromCurrencyCode);
    }

    Currency toCurrency = account.currencies().get(toCurrencyCode);
    if (toCurrency == null) {
      toCurrency = new Currency(toCurrencyCode, BigDecimal.ZERO);
    }

    // Perform the exchange
    BigDecimal exchangedAmount = amount.multiply(rate.rate());
    fromCurrency = new Currency(fromCurrencyCode, fromCurrency.amount().subtract(amount));
    toCurrency = new Currency(toCurrencyCode, toCurrency.amount().add(exchangedAmount));

    // Rebuild the account with the updated currencies
    Map<String, Currency> updatedCurrencies = new HashMap<>(account.currencies());
    updatedCurrencies.put(fromCurrencyCode, fromCurrency);
    updatedCurrencies.put(toCurrencyCode, toCurrency);

    Account updatedAccount = new Account(account.username(), updatedCurrencies);

    // Log the exchange details
    logger.info("Exchange successful: " + amount + " " + fromCurrencyCode + " to " + exchangedAmount + " " + toCurrencyCode);

    return updatedAccount;
  }
}
