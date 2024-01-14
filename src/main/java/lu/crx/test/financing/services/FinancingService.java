package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.dto.QuerySelectionDto;
import lu.crx.test.financing.entities.Creditor;
import lu.crx.test.financing.entities.EarlyPayment;
import lu.crx.test.financing.entities.Invoice;
import lu.crx.test.financing.entities.Purchaser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@AllArgsConstructor
public class FinancingService {

    /**
     * autowiring several services to do financing algorithm
     */
    private final InvoiceService invoiceService;
    private final PurchaserService purchaserService;
    private final CreditorService creditorService;
    private final EarlyPaymentService earlyPaymentService;
    @Transactional
    public void finance() {

        /** eligibleForFinancingIds ->  chooses the eligible purchasers/invoices */
        List<QuerySelectionDto> eligibleForFinancingIds = invoiceService.doEligibleForFinancingQuerySelection();
        if (CollectionUtils.isNotEmpty(eligibleForFinancingIds)){
           /** invoiceQuerySelection -> is a map that has grouped invoices ids and purchasers/creditors related to that invoice */
            Map<Long, List<QuerySelectionDto>> invoiceQuerySelection = eligibleForFinancingIds.stream()
                    .collect(groupingBy(QuerySelectionDto::getInvoiceId));
            /** looping then through that map to make the financing */
                invoiceQuerySelection.forEach((key,list)->{
                    /** currentInvoice -> as said, maps'key is the invoice key, so we take advantage of this fact to extract the list that key holds in the map  */
                    Invoice currentInvoice = invoiceService.findById(key);
                    /** checks for verification of the invoice existence when getting from the db , and list is not null as well */
                    if (Objects.nonNull(currentInvoice)&&CollectionUtils.isNotEmpty(list)){
                        QuerySelectionDto querySelectionDto = list.get(0); // this is the min financing rate
                        if (Objects.nonNull(querySelectionDto)){
                            // the below is the winner Purchaser to run the financing
                            Purchaser purchaser = purchaserService.findById(querySelectionDto.getPurchaseId());
                            // getting the Creator as well
                            Creditor creditor = creditorService.findById(querySelectionDto.getCreatorId());
                            if (Objects.nonNull(purchaser)){
                                /** early payment entity builder, fills up with information of financing and saving that
                                 *  just a comment here, we do not need the rest of purchasers, the winner purchaser is the first top one- that is used right here
                                 *  even though have different purchasers, all of them claim the same invoice and creditors, so the rest of the list is not needed
                                 *
                                 *  after the building, it is saved into the new created table [EARLY_PAYMENT]
                                 */
                                EarlyPayment earlyPayment = earlyPaymentService.saveOne(
                                        EarlyPayment.builder()
                                                // the date it run
                                                .runDate(LocalDate.now())
                                                // which financing rate is used to finance the invoice
                                                .financingWinnerRate((double)querySelectionDto.getFinancingRate())
                                                // who's the winner purchaser
                                                .winnerPurchaser(purchaser.getId())
                                                // for whom creditor
                                                .creatorId(creditor.getId())
                                                // the previous amount - is just for the reference to compare with the early equivalent payment which is the below
                                                .prevAmount((double)currentInvoice.getValueInCents())
                                                // early payment after financing - an amount deducted the finance rate
                                                .earlyAmount((double)(currentInvoice.getValueInCents()-querySelectionDto.getFinancingRate()))
                                                .build()
                                );
                                // after the save of the early payment entity and if that is successfully saved
                                if(Objects.nonNull(earlyPayment)){
                                    /**
                                     * we update the invoice new added field which shows if the algorith runs for that of not - defaulted as false at the very first round
                                     * after that, at this exactly point, that boolean type from false is set to true and is updated as such
                                     * that is done because we do not need to re-run again the same thing that has previously already run and that way we save time for the run of the algorithm
                                     * it worth to say that boolean, is accountable when we decide upon the eligible purchasers/invoices for the algorithm to consider
                                     */
                                    currentInvoice.setFinanced(Boolean.TRUE);
                                    // the point of updating
                                    currentInvoice = invoiceService.updateOne(currentInvoice);
                                    // logging that event
                                    log.info("Updated invoice with id {}, has run: ({})", currentInvoice.getId(), currentInvoice.isFinanced());
                                }
                            }
                        }
                    }
            });
        }
    }

}
