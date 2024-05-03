package pe.mssupport.service.impl;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pe.mssupport.dto.ExchangeRateDto;
import pe.mssupport.exception.NotFoundException;
import pe.mssupport.repository.ExchangeRateRepository;
import pe.mssupport.repository.entity.ExchangeRateEntity;
import pe.mssupport.service.SupportService;
import pe.mssupport.util.TokenUtil;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRateEntity> getAllExchangeRates() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public void saveExchangeRate(String token, ExchangeRateDto request) {
        val userId = getUserIdFromToken(token);
        val exchangeRateEntity = ExchangeRateEntity.builder()
                .startingAmount(request.getAmount())
                .exchangeRate(request.getExchangeRate())
                .finalAmount(request.getAmount() * request.getExchangeRate())
                .originCurrency(request.getOriginCurrency())
                .targetCurrency(request.getTargetCurrency())
                .creationUser(userId)
                .creationDate(OffsetDateTime.now())
                .updateUser(userId)
                .updateDate(OffsetDateTime.now()).build();

        exchangeRateRepository.save(exchangeRateEntity);
    }

    @Override
    public void updateExchangeRate(String token, Integer id, ExchangeRateDto request) {
        val userId = getUserIdFromToken(token);
        val exchangeRateEntity = exchangeRateRepository.findById(id);

        if (exchangeRateEntity.isEmpty()) throw new NotFoundException("Record Not Found");

        val exchangeRateForUpdate = exchangeRateEntity.get();
        exchangeRateForUpdate.setStartingAmount(request.getAmount());
        exchangeRateForUpdate.setExchangeRate(request.getExchangeRate());
        exchangeRateForUpdate.setFinalAmount(request.getAmount() * request.getExchangeRate());
        exchangeRateForUpdate.setOriginCurrency(request.getOriginCurrency());
        exchangeRateForUpdate.setTargetCurrency(request.getTargetCurrency());
        exchangeRateForUpdate.setUpdateUser(userId);
        exchangeRateForUpdate.setUpdateDate(OffsetDateTime.now());

        exchangeRateRepository.save(exchangeRateForUpdate);
    }

    @Override
    public void deleteExchangeRate(String token, Integer id, ExchangeRateDto request) {
        exchangeRateRepository.deleteById(id);
    }

    private Integer getUserIdFromToken(String token) {
        val decodedJWT = TokenUtil.decodeToken(token);

        if (decodedJWT == null || decodedJWT.getClaim("userId") == null)
            throw new HttpClientErrorException(HttpStatusCode.valueOf(401));

        return decodedJWT.getClaim("userId").asInt();
    }
}
