package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.Creditor;
import lu.crx.test.financing.entities.FinancingSettings;
import lu.crx.test.financing.repository.FinancingSettingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author Andreas Karmenis
 * @created 14/01/2024 - 3:11 PM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FinancingSettingsServiceTest {
    @Mock
    private FinancingSettingsRepository financingSettingsRepository;

    @Test
    public void createFinancingSettingsTest() throws Exception {

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

        FinancingSettings fs1 = FinancingSettings.builder().id(7L)
                .creditor(coffeeBeansLLC)
                .annualRateInBps(50)
                .build();

        FinancingSettingsService financingSettingsService = new FinancingSettingsService(financingSettingsRepository);
        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(financingSettingsRepository.findById(7L))
                .thenReturn(Optional.of(fs1));

    }
}