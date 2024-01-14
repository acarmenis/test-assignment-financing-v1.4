package lu.crx.test.financing.services;

import lombok.AllArgsConstructor;
import lu.crx.test.financing.entities.FinancingSettings;
import lu.crx.test.financing.repository.FinancingSettingsRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andreas Karmenis
 * @created 14/01/2024 - 3:09 PM
 * @project test-assignment-financing-v1.4
 */
@Service
@AllArgsConstructor
@Transactional
public class FinancingSettingsService {
    private final FinancingSettingsRepository financingSettingsRepository;
    public FinancingSettings findById(Long id){
        return financingSettingsRepository.findById(id).orElse(null);
    }
    public List<FinancingSettings> findAll(){
        List<FinancingSettings> financingSettings = financingSettingsRepository.findAll();
        return CollectionUtils.isNotEmpty(financingSettings)?financingSettings:new ArrayList<>();
    }
}
