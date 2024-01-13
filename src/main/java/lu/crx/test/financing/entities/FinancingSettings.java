package lu.crx.test.financing.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Andreas Karmenis
 * @created 11/01/2024 - 3:33 PM
 * @project test-assignment-financing-v1.4
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancingSettings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(optional = false)
    private Creditor creditor;
    /** The annual financing rate set by the purchaser for this creditor. */
    @Basic(optional = false)
    private int annualRateInBps;
    @Singular
    @OneToMany(mappedBy = "financingSettings", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaserFinancingSettings> purchaserFinancingSettings = new HashSet<>();
}
