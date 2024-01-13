package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.Purchaser;
import lu.crx.test.financing.repository.PurchaserRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 09/01/2024 - 12:53 PM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class PurchaserService {
    private final PurchaserRepository purchaserRepository;
    public Purchaser findById(Long id){
        return purchaserRepository.findById(id).orElse(null);
    }
    public List<Purchaser> findAll(){
        List<Purchaser> purchasers = purchaserRepository.findAll();
        return CollectionUtils.isNotEmpty(purchasers)?purchasers:new ArrayList<>();
    }

}
