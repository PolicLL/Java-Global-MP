package task5.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import task5.exception.InsufficientFundsException;
import task5.model.Account;
import task5.model.CurrencyType;
import task5.model.ExchangeRate;

public class ExchangeService {
  private static final Logger logger = Logger.getLogger(ExchangeService.class.getName());

  public Account exchangeCurrency(Account account, CurrencyType fromCurrency,
      CurrencyType toCurrency, BigDecimal amount, ExchangeRate rate) throws InsufficientFundsException {

    BigDecimal fromAmount = account.wallet().get(fromCurrency);

    if (fromAmount == null || fromAmount.compareTo(amount) < 0) {
      throw new InsufficientFundsException("Insufficient funds in " + fromCurrency);
    }

    BigDecimal toAmount = account.wallet().getOrDefault(toCurrency, BigDecimal.ZERO);

    BigDecimal exchangedAmount = amount.multiply(rate.rate());

    Map<CurrencyType, BigDecimal> updatedCurrencies = new HashMap<>(account.wallet());
    updatedCurrencies.put(fromCurrency, fromAmount.subtract(amount));
    updatedCurrencies.put(toCurrency, toAmount.add(exchangedAmount));

    Account updatedAccount = new Account(account.username(), updatedCurrencies);

    logger.info("Exchange successful: " + amount + " " + fromCurrency + " to "
        + exchangedAmount + " " + toCurrency);

    return updatedAccount;
  }
}
