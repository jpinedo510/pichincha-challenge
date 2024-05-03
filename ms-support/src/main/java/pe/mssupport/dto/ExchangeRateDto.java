package pe.mssupport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDto implements Serializable {

    private static final long serialVersionUID = 7803466481018315285L;

    private double amount;
    private String originCurrency;
    private String targetCurrency;
    private double exchangeRate;
}
