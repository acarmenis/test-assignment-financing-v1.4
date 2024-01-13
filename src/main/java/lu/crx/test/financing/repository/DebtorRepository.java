package lu.crx.test.financing.repository;

import lu.crx.test.financing.entities.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:15 AM
 * @project test-assignment-financing-v1.4
 */
public interface DebtorRepository extends JpaRepository<Debtor, Long> {}
