package lu.crx.test.financing.repository;


import lu.crx.test.financing.entities.FinancingSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Andreas Karmenis
 * @created 14/01/2024 - 3:08 PM
 * @project test-assignment-financing-v1.4
 */
public interface FinancingSettingsRepository extends JpaRepository<FinancingSettings, Long>,
        JpaSpecificationExecutor<FinancingSettings> {}