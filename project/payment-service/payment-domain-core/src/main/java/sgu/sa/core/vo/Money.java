package sgu.sa.core.vo;

import sgu.sa.core.exception.InvalidMoneyException;
import lombok.NonNull;
import sgu.sa.core.type.Currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public record Money(BigDecimal value, Currency currency) {
    public Money {
        if (value == null || currency == null) {
            throw new InvalidMoneyException("Số tiền và đơn vị tiền tệ không được null");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidMoneyException("Giá trị tiền phải lớn hơn 0");
        }
    }

    @Override
    public @NonNull String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat df = new DecimalFormat("#,##0.##", symbols);
        df.setMaximumFractionDigits(2);

        return df.format(value) + " " + currency.name();
    }
}
