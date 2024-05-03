package pe.mssupport.service;

import pe.mssupport.dto.ExchangeRateDto;
import pe.mssupport.repository.entity.ExchangeRateEntity;
import java.util.List;

public interface SupportService {

    public List<ExchangeRateEntity> getAllExchangeRates();

    public void saveExchangeRate(String token, ExchangeRateDto request);

    public void updateExchangeRate(String token, Integer id, ExchangeRateDto request);

    public void deleteExchangeRate(String token, Integer id);
}
