package task5.model;

import java.math.BigDecimal;
import java.util.Map;

public record Account(String username, Map<CurrencyType, BigDecimal> wallet) {}

