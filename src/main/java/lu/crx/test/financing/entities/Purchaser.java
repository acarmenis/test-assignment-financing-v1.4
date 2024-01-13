package lu.crx.test.financing.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Purchaser is an entity (usually a bank) that wants to purchase the invoices. I.e. it issues a loan
 * to the creditor for the term and the value of the invoice, according to the rate set up by this purchaser.
 */
@Entity
@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchaser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic(optional = false)
    private String name;
    /**
     * The minimum financing term (duration between the financing date and the maturity date of the invoice).
     *  should be greater or equal to the value Purchaser.minimumFinancingTermInDays
     */
    @Basic(optional = false)
    private int minimumFinancingTermInDays;
    /**
     * The per-creditor settings for financing.
     * Of all purchasers eligible for financing, select the one with the lowest financing rate.
     * This will be the Purchaser that finances the invoice.
     */
    @Singular
    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaserFinancingSettings> purchaserFinancingSettings = new HashSet<>();
}
