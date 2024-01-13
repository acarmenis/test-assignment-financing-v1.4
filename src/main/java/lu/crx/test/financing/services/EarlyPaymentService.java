package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.crx.test.financing.entities.EarlyPayment;
import lu.crx.test.financing.repository.EarlyPaymentRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 13/01/2024 - 9:00 PM
 * @project test-assignment-financing-v1.4
 */
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class EarlyPaymentService {
    private final EarlyPaymentRepository earlyPaymentRepository;
    public EarlyPayment findById(Long id){
        return earlyPaymentRepository.findById(id).orElse(null);
    }
    public List<EarlyPayment> findAll(){
        List<EarlyPayment> earlyPayments = earlyPaymentRepository.findAll();
        return CollectionUtils.isNotEmpty(earlyPayments)?earlyPayments:new ArrayList<>();
    }
    public EarlyPayment saveOne(EarlyPayment earlyPayment){
        return earlyPaymentRepository.saveAndFlush(earlyPayment);
    }
}
