package lu.crx.test.financing.repository;

import lu.crx.test.financing.entities.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andreas Karmenis
 * @created 08/01/2024 - 11:51 PM
 * @project test-assignment-financing-v1.4
 */
public interface CreditorRepository extends JpaRepository<Creditor, Long> {}
