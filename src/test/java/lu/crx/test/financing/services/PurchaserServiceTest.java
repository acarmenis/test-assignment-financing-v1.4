/*
package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.Creditor;
import lu.crx.test.financing.entities.Purchaser;
import lu.crx.test.financing.entities.PurchaserFinancingSettings;
import lu.crx.test.financing.repository.PurchaserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

*/
/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:57 PM
 * @project test-assignment-financing-v1.4
 *//*

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PurchaserServiceTest {
    @Mock
    private PurchaserRepository purchaserRepository;

    @Test
    public void getDebtorServiceGetByIdTest() throws Exception {

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

        Creditor beanstalk = Creditor.builder()
                .id(3)
                .name("Beanstalk")
                .maxFinancingRateInBps(2)
                .build();


        PurchaserFinancingSettings coffeeBeansLLCSettings = PurchaserFinancingSettings.builder()
                .id(8)
                .creditor(coffeeBeansLLC)
                .annualRateInBps(50)
                .build();

        PurchaserFinancingSettings homeBrewSettings = PurchaserFinancingSettings.builder()
                .id(9)
                .creditor(homeBrew)
                .annualRateInBps(60)
                .build();

        PurchaserFinancingSettings fatBankSettings = PurchaserFinancingSettings.builder()
                .id(10)
                .creditor(beanstalk)
                .annualRateInBps(30)
                .build();

        Purchaser richBank =  Purchaser.builder()
                .id(7)
                .name("RichBank")
                .purchaserFinancingSetting(coffeeBeansLLCSettings)
                .purchaserFinancingSetting(homeBrewSettings)
                .purchaserFinancingSetting(fatBankSettings)
                .minimumFinancingTermInDays(10)
                .build();

        Purchaser fatBank = Purchaser.builder()
                .id(11)
                .name("FatBank")
                .minimumFinancingTermInDays(12)
                .build();

        Mockito.lenient()// use the lenient method to modify the strictness of object mocking
                .when(purchaserRepository.findById(7L))
                .thenReturn(Optional.of(richBank));

    }
}*/
