package pe.mssupport.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pe.mssupport.dto.ExchangeRateDto;
import pe.mssupport.repository.entity.ExchangeRateEntity;
import pe.mssupport.service.SupportService;
import java.util.List;

@RestController
@RequestMapping(path = "v1/support")
public class SupportController {

    @Autowired
    private SupportService supportService;

    @GetMapping(path = "/exchange-rate")
    public List<ExchangeRateEntity> getAllExchangeRates() {
        return supportService.getAllExchangeRates();
    }

    @PostMapping(path = "/exchange-rate")
    public void saveExchangeRate(@RequestHeader HttpHeaders headers,
                                 @RequestBody ExchangeRateDto request) {
        val token = validateHeaderAndGetToken(headers);
        supportService.saveExchangeRate(token, request);
    }

    @PutMapping(path = "/exchange-rate/{id}")
    public void updateExchangeRate(@RequestHeader HttpHeaders headers,
                                   @PathVariable("id") Integer id,
                                   @RequestBody ExchangeRateDto request) {
        val token = validateHeaderAndGetToken(headers);
        supportService.updateExchangeRate(token, id, request);
    }

    @DeleteMapping(path = "/exchange-rate/{id}")
    public void deleteExchangeRate(@RequestHeader HttpHeaders headers,
                                   @PathVariable("id") Integer id,
                                   @RequestBody ExchangeRateDto request) {
        val token = validateHeaderAndGetToken(headers);
        supportService.deleteExchangeRate(token, id, request);
    }

    private String validateHeaderAndGetToken(HttpHeaders headers) {
        String header = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer"))
            throw new HttpClientErrorException(HttpStatusCode.valueOf(401));

        return header.split(" ")[1];
    }
}
