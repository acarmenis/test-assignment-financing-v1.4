package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.Debtor;
import lu.crx.test.financing.repository.DebtorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:30 AM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
/**
 * MockitoExtension extension is needed to create mock objects through annotations.
 * This code tells Mockito to create a mock object of type DebtorRepository
 */
@ExtendWith(MockitoExtension.class)
public class DebtorServiceTest {
    /**
     * this extension is needed to create mock objects through annotations
     */
    @Mock
    private DebtorRepository debtorRepository;

    @Test
    public void getDebtorServiceGetByIdTest() throws Exception {

        Debtor chocolateFactory =  Debtor.builder()
                .id(4)
                .name("Chocolate Factory")
                .build();
        Debtor sweetsInc =  Debtor.builder()
                .id(5)
                .name("Sweets Inc")
                .build();

        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(debtorRepository.findById(4L))
                .thenReturn(Optional.of(chocolateFactory));

        DebtorService debtorService = new DebtorService(debtorRepository);
        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(debtorRepository.findById(5L))
                .thenReturn(Optional.of(sweetsInc));
    }

}