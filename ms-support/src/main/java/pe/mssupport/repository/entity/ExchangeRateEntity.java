package pe.mssupport.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exchange_rate")
public class ExchangeRateEntity implements Serializable {

    private static final long serialVersionUID = -7616868984844056740L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "starting_amount")
    private double startingAmount;

    @Column(name = "final_amount")
    private double finalAmount;

    @Column(name = "exchange_rate")
    private double exchangeRate;

    @Column(name = "origin_currency")
    private String originCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "creation_user")
    private Integer creationUser;

    @Column(name = "creation_date", updatable = false, nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private OffsetDateTime updateDate;
}
