package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.EarlyPayment;
import lu.crx.test.financing.repository.EarlyPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Andreas Karmenis
 * @created 14/01/2024 - 9:41 PM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class EarlyPaymentServiceTest {
    @Mock
    private EarlyPaymentRepository earlyPaymentRepository;

    @Test
    public void getInvoiceServiceSelectEligibleInvoicesAndPurchasersNumberTest() throws Exception {
        EarlyPayment ep = EarlyPayment.builder()
                .id(43L)
                .runDate(LocalDate.now())
                .financingWinnerRate((double)1)
                .winnerPurchaser(16L)
                .creatorId(2L)
                .prevAmount((double)500000)
                .earlyAmount((double)499999)
                .build();
        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(earlyPaymentRepository.findById(43L))
                .thenReturn(Optional.of(ep));
    }
}