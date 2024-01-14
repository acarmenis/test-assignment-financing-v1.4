package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lu.crx.test.financing.dto.QuerySelectionDto;
import lu.crx.test.financing.entities.Invoice;
import lu.crx.test.financing.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 8:17 PM
 * @project test-assignment-financing-v1.4
 */
@Service
@AllArgsConstructor
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

     public Invoice findById(Long id){
        return invoiceRepository.findById(id).orElse(null);
    }

    /**
     *
     * @return  List<QuerySelectionDto> which has the eligible
     * purchasers/invoices/creditors and so on that is done directly from SQL / HQPL
     * in order to minimize the process since the SQL process is far faster comparing to tbe done via java/jpa
     *
     * please, take a look at the InvoiceRepository repository
     */
    public List<QuerySelectionDto> doEligibleForFinancingQuerySelection(){
      return invoiceRepository.doEligibleForFinancingQuerySelection();
    }

    public Long countTableRecords(){
       return invoiceRepository.count();
    }

    public Invoice updateOne(Invoice invoice){
        return invoiceRepository.saveAndFlush(invoice);
    }

}
