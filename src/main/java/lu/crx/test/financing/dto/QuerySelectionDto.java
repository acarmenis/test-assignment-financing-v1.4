package lu.crx.test.financing.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 7:06 PM
 * @project test-assignment-financing-v1.4
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuerySelectionDto {
    private Long purchaseId;
    private Long invoiceId;
    private Long creatorId;
    private Long debtorId;
    private LocalDate maturityDate;
    private Integer minFinTermInDays;
    private Integer annualBpsRate;
    private Integer maxFinBpsRate;
    private Integer financingTerm;
    private Integer financingRate;
}
