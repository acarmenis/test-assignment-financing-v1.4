package lu.crx.test.financing.repository;

import lu.crx.test.financing.entities.PurchaserFinancingSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Andreas Karmenis
 * @created 11/01/2024 - 6:10 PM
 * @project test-assignment-financing-v1.4
 */
public interface PurchaserFinancingSettingsRepository
        extends JpaRepository<PurchaserFinancingSettings, Long>,
        JpaSpecificationExecutor<PurchaserFinancingSettings> {}
