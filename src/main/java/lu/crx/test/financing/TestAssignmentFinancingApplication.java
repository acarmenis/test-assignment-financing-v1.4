package lu.crx.test.financing;

import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.services.FinancingService;
import lu.crx.test.financing.services.InvoiceService;
import lu.crx.test.financing.services.SeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication
public class TestAssignmentFinancingApplication {

    /**
     *  InvoiceService invoiceService; autowired
     */
    @Autowired
    private InvoiceService invoiceService;
    public static void main(String[] args) {
        SpringApplication.run(TestAssignmentFinancingApplication.class, args);
    }

    @Bean
    public CommandLineRunner run( SeedingService seedingService,  FinancingService financingService) {
        return args -> {
                    CompletableFuture<LocalDateTime> asynchronouslyRun = CompletableFuture.supplyAsync(() -> {
                        try {
                             doRun(seedingService, financingService);
                        } catch (Exception e) {
                          throw new IllegalStateException(e);
                        }
                        return LocalDateTime.now();
                    }).thenApply(ended -> {
                    return ended;
                });
            log.info("Financing completed at: {}",asynchronouslyRun.get());
        };
    }

    private void doRun(SeedingService seedingService,  FinancingService financingService){
        /**
         * It counts the invoice's table records in order to detect if at start time, the
         * seedMasterData & seedInvoices should be activated or not
         */
        Long records = invoiceService.countTableRecords();
        if(Objects.isNull(records)||records==0){
            // seeding master data - creditors, debtors and purchasers
            seedingService.seedMasterData();
            // seeding the invoices
            seedingService.seedInvoices();
        }
        // running the financing
        log.info("Financing started at: {}", LocalDateTime.now());
        financingService.finance();
    }
}
