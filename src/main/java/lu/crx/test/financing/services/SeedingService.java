package lu.crx.test.financing.services;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SeedingService {

    private EntityManager entityManager;

    private Creditor creditor1;
    private Creditor creditor2;
    private Creditor creditor3;

    private Debtor debtor1;
    private Debtor debtor2;
    private Debtor debtor3;

    private Purchaser purchaser1;
    private Purchaser purchaser2;
    private Purchaser purchaser3;

    public SeedingService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void seedMasterData() {
        log.info("Seeding master data");

        // creditors
        creditor1 = Creditor.builder()
                .name("Coffee Beans LLC")
                .maxFinancingRateInBps(5)
                .build();
        entityManager.persist(creditor1);

        creditor2 = Creditor.builder()
                .name("Home Brew")
                .maxFinancingRateInBps(3)
                .build();
        entityManager.persist(creditor2);

        creditor3 = Creditor.builder()
                .name("Beanstalk")
                .maxFinancingRateInBps(2)
                .build();
        entityManager.persist(creditor3);

        // debtors
        debtor1 = Debtor.builder()
                .name("Chocolate Factory")
                .build();
        entityManager.persist(debtor1);

        debtor2 = Debtor.builder()
                .name("Sweets Inc")
                .build();
        entityManager.persist(debtor2);

        debtor3 = Debtor.builder()
                .name("ChocoLoco")
                .build();
        entityManager.persist(debtor3);



        FinancingSettings fsc1 = FinancingSettings.builder()
                .creditor(creditor1)
                .annualRateInBps(50)
                .build();
        FinancingSettings fsc2 = FinancingSettings.builder()
                .creditor(creditor2)
                .annualRateInBps(60)
                .build();
        FinancingSettings fsc3 = FinancingSettings.builder()
                .creditor(creditor3)
                .annualRateInBps(30)
                .build();
        FinancingSettings fsc4 = FinancingSettings.builder()
                .creditor(creditor1)
                .annualRateInBps(40)
                .build();
        FinancingSettings fsc5 = FinancingSettings.builder()
                .creditor(creditor2)
                .annualRateInBps(80)
                .build();
        FinancingSettings fsc6 = FinancingSettings.builder()
                .creditor(creditor3)
                .annualRateInBps(25)
                .build();
        FinancingSettings fsc7 = FinancingSettings.builder()
                .creditor(creditor1)
                .annualRateInBps(30)
                .build();
        FinancingSettings fsc8 = FinancingSettings.builder()
                .creditor(creditor2)
                .annualRateInBps(50)
                .build();
        FinancingSettings fsc9 = FinancingSettings.builder()
                .creditor(creditor3)
                .annualRateInBps(45)
                .build();

        // purchasers
        purchaser1 = Purchaser.builder()
                .name("RichBank")
                .minimumFinancingTermInDays(10)
                .build();
        purchaser2 = Purchaser.builder()
                .name("FatBank")
                .minimumFinancingTermInDays(12)
                .build();
        purchaser3 = Purchaser.builder()
                .name("MegaBank")
                .minimumFinancingTermInDays(8)
                .build();

        LocalDate now = LocalDate.now();
        PurchaserFinancingSettings.builder()
                .purchaser(purchaser1)
                .financingSettings(fsc1)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs1 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser1)
                .financingSettings(fsc1)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs2 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser1)
                .financingSettings(fsc2)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs3 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser1)
                .financingSettings(fsc3)
                .runAt(now)
                .build();

        final PurchaserFinancingSettings pfs4 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser2)
                .financingSettings(fsc4)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs5 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser2)
                .financingSettings(fsc5)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs6 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser2)
                .financingSettings(fsc6)
                .runAt(now)
                .build();

        final PurchaserFinancingSettings pfs7 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser3)
                .financingSettings(fsc7)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs8 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser3)
                .financingSettings(fsc8)
                .runAt(now)
                .build();
        final PurchaserFinancingSettings pfs9 = PurchaserFinancingSettings.builder()
                .purchaser(purchaser3)
                .financingSettings(fsc9)
                .runAt(now)
                .build();

        List<FinancingSettings> financingSettings = List.of(fsc1,fsc2,fsc3,fsc4,fsc5,fsc6,fsc7,fsc8,fsc9);
        List<Purchaser> purchasers = List.of(purchaser1,purchaser2,purchaser3);
        List<PurchaserFinancingSettings> purchaserFinancingSettings = List.of(pfs1,pfs2,pfs3,pfs4,pfs5,pfs6,pfs7,pfs8,pfs9);

        financingSettings.forEach( e -> {
            entityManager.persist(e);
        });
        purchasers.forEach( e -> {
            entityManager.persist(e);
        });
        purchaserFinancingSettings.forEach( e -> {
            entityManager.persist(e);
        });


     /*   purchaser1 = Purchaser.builder()
                .name("RichBank")
                .minimumFinancingTermInDays(10)
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor1)
                        .annualRateInBps(50)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor2)
                        .annualRateInBps(60)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor3)
                        .annualRateInBps(30)
                        .build())
                .build();
        entityManager.persist(purchaser1);*/

   /*     purchaser2 = Purchaser.builder()
                .name("FatBank")
                .minimumFinancingTermInDays(12)
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor1)
                        .annualRateInBps(40)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor2)
                        .annualRateInBps(80)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor3)
                        .annualRateInBps(25)
                        .build())
                .build();
        entityManager.persist(purchaser2);*/

     /*   purchaser3 = Purchaser.builder()
                .name("MegaBank")
                .minimumFinancingTermInDays(8)
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor1)
                        .annualRateInBps(30)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor2)
                        .annualRateInBps(50)
                        .build())
                .purchaserFinancingSetting(PurchaserFinancingSettings.builder()
                        .creditor(creditor3)
                        .annualRateInBps(45)
                        .build())
                .build();*/

    }








    @Transactional
    public void seedInvoices() {
        log.info("Seeding the invoices");

        entityManager.persist(Invoice.builder()
                .creditor(creditor1)
                .debtor(debtor1)
                .valueInCents(200000)
                .maturityDate(LocalDate.now().plusDays(52))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor1)
                .debtor(debtor2)
                .valueInCents(800000)
                .maturityDate(LocalDate.now().plusDays(33))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor1)
                .debtor(debtor3)
                .valueInCents(600000)
                .maturityDate(LocalDate.now().plusDays(43))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor1)
                .debtor(debtor1)
                .valueInCents(500000)
                .maturityDate(LocalDate.now().plusDays(80))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor1)
                .debtor(debtor2)
                .valueInCents(6000000)
                .maturityDate(LocalDate.now().plusDays(5))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor2)
                .debtor(debtor3)
                .valueInCents(500000)
                .maturityDate(LocalDate.now().plusDays(10))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor2)
                .debtor(debtor1)
                .valueInCents(800000)
                .maturityDate(LocalDate.now().plusDays(15))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor2)
                .debtor(debtor2)
                .valueInCents(9000000)
                .maturityDate(LocalDate.now().plusDays(30))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor2)
                .debtor(debtor3)
                .valueInCents(450000)
                .maturityDate(LocalDate.now().plusDays(32))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor2)
                .debtor(debtor1)
                .valueInCents(800000)
                .maturityDate(LocalDate.now().plusDays(11))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor3)
                .debtor(debtor2)
                .valueInCents(3000000)
                .maturityDate(LocalDate.now().plusDays(10))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor3)
                .debtor(debtor3)
                .valueInCents(5000000)
                .maturityDate(LocalDate.now().plusDays(14))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor3)
                .debtor(debtor1)
                .valueInCents(9000000)
                .maturityDate(LocalDate.now().plusDays(23))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor3)
                .debtor(debtor2)
                .valueInCents(800000)
                .maturityDate(LocalDate.now().plusDays(18))
                .build());

        entityManager.persist(Invoice.builder()
                .creditor(creditor3)
                .debtor(debtor3)
                .valueInCents(9000000)
                .maturityDate(LocalDate.now().plusDays(50))
                .build());
    }

}
