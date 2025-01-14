package task5.model;

import java.math.BigDecimal;

public record ExchangeRate(CurrencyType fromCurrency, CurrencyType toCurrency, BigDecimal rate) {}

