package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lu.crx.test.financing.entities.Debtor;
import lu.crx.test.financing.repository.DebtorRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:15 AM
 * @project test-assignment-financing-v1.4
 */
@Service
@AllArgsConstructor
@Transactional
public class DebtorService {
    private final DebtorRepository debtorRepository;
    public Debtor findById(Long id){
        return debtorRepository.findById(id).orElse(null);
    }
    public List<Debtor> findAll(){
        List<Debtor> deptors = debtorRepository.findAll();
        return CollectionUtils.isNotEmpty(deptors)?deptors:new ArrayList<>();
    }
}
