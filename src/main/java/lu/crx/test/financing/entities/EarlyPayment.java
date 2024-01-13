package lu.crx.test.financing.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Andreas Karmenis
 * @created 13/01/2024 - 8:51 PM
 * @project test-assignment-financing-v1.4
 */
@Entity
@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="RUN_DATE")
    private LocalDate runDate;
    @Basic(optional = false)
    private Double financingWinnerRate;
    @Basic(optional = false)
    private Long winnerPurchaser;
    @Basic(optional = false)
    private Long creatorId;
    @Basic(optional = false)
    private Double prevAmount;
    @Basic(optional = false)
    private Double earlyAmount;
}
