package lu.crx.test.financing.repository;

import lu.crx.test.financing.entities.EarlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andreas Karmenis
 * @created 13/01/2024 - 8:59 PM
 * @project test-assignment-financing-v1.4
 */
public interface EarlyPaymentRepository extends JpaRepository<EarlyPayment, Long> {}
