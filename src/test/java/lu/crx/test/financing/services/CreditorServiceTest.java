package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.Creditor;
import lu.crx.test.financing.repository.CreditorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:33 PM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class CreditorServiceTest {
    @Mock
    private CreditorRepository creditorRepository;

    @Test
    public void getCreditorServiceGetByIdTest() throws Exception {

        Creditor coffeeBeansLLC = Creditor.builder()
                .id(1)
                .name("Coffee Beans LLC")
                .maxFinancingRateInBps(5)
                .build();
        Creditor homeBrew = Creditor.builder()
                .id(2)
                .name("Home Brew")
                .maxFinancingRateInBps(3)
                .build();

        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(creditorRepository.findById(1L))
                .thenReturn(Optional.of(coffeeBeansLLC));

        CreditorService creditorService = new CreditorService(creditorRepository);
        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(creditorRepository.findById(2L))
                .thenReturn(Optional.of(homeBrew));
    }
}
