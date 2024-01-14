## To run application

The application in this repository: 
* mvn clean install -U 
* docker build -t crx-markets .
* docker run -p 8080:8080 crx-markets

## What has been done

TestAssignmentFinancingApplication is slightly changed. At the CommandLineRunner, inserted a private method
private void doRun(SeedingService seedingService,  FinancingService financingService)
That method, is executed under a condition. The condition has to do with avoiding running each time 
the seedingService.seedMasterData(); & seedingService.seedInvoices(); as long as the database 
has previously been populated. The financingService.finance(); however, needs to be executed either way.

The private method, is executed asynchronously, in order to avoid freezing of the application in a separate thread.
That is the purpose of using the
CompletableFuture<LocalDateTime> asynchronouslyRun = CompletableFuture.supplyAsync(() -> {..

It, at the end, returns the end time execution date time in order to mark the end of financing run time.
Previously, is marked the starting date time just before calling it.

The EarlyPayment entity is added to serve as early financing invoices.
FinancingSettings and Purchaser are one-to-many tables with the joint table of PurchaserFinancingSettings.

QuerySelectionDto, is an temp dto to be used for selecting eligible purchasers, invoices, creditors e.t.c from a list of 
which, FinancingService, does further mappings to prepare collection for financing.

Below can be found SQL scripts to run against the H2 db - if desire

    select * from CREDITOR;
    select * from DEBTOR;
    select * from INVOICE;
    select * from PURCHASER;
    select * from PURCHASER_FINANCING_SETTINGS;
    select * from FINANCING_SETTINGS;
    select * from EARLY_PAYMENT; 
    SELECT * FROM INFORMATION_SCHEMA.TABLES;

 
    SELECT
    p.ID purchaser,
    c.ID creditor,
    i.ID invoice,
    D.ID deptor,
    i.MATURITY_DATE,
    p.MINIMUM_FINANCING_TERM_IN_DAYS,
    fs.ANNUAL_RATE_IN_BPS,
    c.MAX_FINANCING_RATE_IN_BPS,
    DATEDIFF('DAY', CURRENT_DATE(), i.MATURITY_DATE) as financingTerm,
    (fs.ANNUAL_RATE_IN_BPS * DATEDIFF('DAY', CURRENT_DATE(), i.MATURITY_DATE) / 360)  as "financing rate"
    FROM INVOICE i
    LEFT JOIN CREDITOR C ON c.ID = i.CREDITOR_ID AND i.IS_FINANCED = false
    LEFT JOIN DEBTOR D ON D.ID = i.DEBTOR_ID
    JOIN FINANCING_SETTINGS fs ON c.ID = fs.CREDITOR_ID
    JOIN PURCHASER_FINANCING_SETTINGS pfs ON fs.ID = pfs.FINANCING_SETTINGS_ID
    JOIN PURCHASER p ON p.ID = pfs.PURCHASER_ID
    WHERE DATEDIFF('DAY', CURRENT_DATE(), i.MATURITY_DATE) >= p.MINIMUM_FINANCING_TERM_IN_DAYS
    AND (fs.ANNUAL_RATE_IN_BPS * DATEDIFF('DAY', CURRENT_DATE(), i.MATURITY_DATE) / 360) <= c.MAX_FINANCING_RATE_IN_BPS
    AND (fs.ANNUAL_RATE_IN_BPS * DATEDIFF('DAY', CURRENT_DATE(), i.MATURITY_DATE) / 360) != 0
    -- GROUP BY p.ID,  c.ID, i.ID,"financing rate"
    ORDER BY  i.ID, "financing rate";