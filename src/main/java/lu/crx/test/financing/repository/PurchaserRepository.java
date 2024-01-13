package lu.crx.test.financing.repository;

import lu.crx.test.financing.entities.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:52 PM
 * @project test-assignment-financing-v1.4
 */
public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {}
