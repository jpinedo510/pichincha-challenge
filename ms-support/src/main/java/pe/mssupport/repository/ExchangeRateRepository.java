package pe.mssupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.mssupport.repository.entity.ExchangeRateEntity;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Integer> {
}
