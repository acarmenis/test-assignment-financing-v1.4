package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lu.crx.test.financing.entities.Creditor;
import lu.crx.test.financing.repository.CreditorRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 08/01/2024 - 11:53 PM
 * @project test-assignment-financing-v1.4
 */
@Service
@AllArgsConstructor
@Transactional
public class CreditorService {
    private final CreditorRepository creditorRepository;
    public Creditor findById(Long id){
        return creditorRepository.findById(id).orElse(null);
    }
    public List<Creditor> findAll(){
        List<Creditor> creditors = creditorRepository.findAll();
        return CollectionUtils.isNotEmpty(creditors)?creditors:new ArrayList<>();
    }
}
