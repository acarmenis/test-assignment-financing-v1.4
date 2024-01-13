package lu.crx.test.financing.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Financing settings set by the purchaser for a specific creditor.
 */
@Entity
@Table(
  uniqueConstraints = { @UniqueConstraint( name = "UniquePurchaserAndFinancingSettings", columnNames = {"PURCHASER_ID", "FINANCING_SETTINGS_ID", "RUN_AT"})},
  indexes = @Index(name = "purchaserFinanceSettingsIndex", columnList = "PURCHASER_ID, FINANCING_SETTINGS_ID DESC"))
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserFinancingSettings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "PURCHASER_ID")
    private Purchaser purchaser;
    @ManyToOne(optional = false)
    @JoinColumn(name = "FINANCING_SETTINGS_ID")
    private FinancingSettings financingSettings;
    @Column(name="RUN_AT")
    private LocalDate runAt;

}
