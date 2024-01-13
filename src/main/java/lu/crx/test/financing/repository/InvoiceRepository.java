package lu.crx.test.financing.repository;

import lu.crx.test.financing.dto.QuerySelectionDto;
import lu.crx.test.financing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 5:17 PM
 * @project test-assignment-financing-v1.4
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    @Query(value = "SELECT DISTINCT new lu.crx.test.financing.dto.QuerySelectionDto(p.id, i.id, c.id, d.id, i.maturityDate, p.minimumFinancingTermInDays,fs.annualRateInBps, c.maxFinancingRateInBps, (DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate)), ((fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate)/360))) " +
                   "FROM Invoice i " +
                   "LEFT JOIN Creditor c ON c.id = i.creditor.id AND i.isFinanced = false " +
                   "LEFT JOIN Debtor d ON d.id = i.debtor.id " +
                   "JOIN FinancingSettings fs ON c.id = fs.creditor.id " +
                   "RIGHT JOIN PurchaserFinancingSettings pfs ON fs.id = pfs.financingSettings.id " +
                   "RIGHT JOIN Purchaser p ON p.id = pfs.purchaser.id " +
                   "WHERE DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) >= p.minimumFinancingTermInDays " +
                   "AND (fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) / 360) <= c.maxFinancingRateInBps " +
                   "AND (fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) / 360) != 0 " +
                   // "GROUP BY p.id, c.id, i.id, (fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) / 360)"+
                   // "ORDER BY (fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) / 360) ASC ")
                   "ORDER BY i.id, (fs.annualRateInBps * DATEDIFF('DAY', CURRENT_DATE(), i.maturityDate) / 360) ASC ")
    List<QuerySelectionDto> doEligibleForFinancingQuerySelection();


}
