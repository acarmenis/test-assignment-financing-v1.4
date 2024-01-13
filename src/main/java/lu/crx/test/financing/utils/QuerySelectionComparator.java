package lu.crx.test.financing.utils;

import lu.crx.test.financing.dto.QuerySelectionDto;

import java.util.Comparator;

/**
 * @author Andreas Karmenis
 * @created 13/01/2024 - 8:04 PM
 * @project test-assignment-financing-v1.4
 */
public class QuerySelectionComparator implements Comparator<QuerySelectionDto> {
    @Override
    public int compare(QuerySelectionDto o1, QuerySelectionDto o2) {
        return o1.getFinancingRate() - o2.getFinancingRate();
    }
}
