package phonebook.algorithms.search;

import java.util.List;

public class BinarySearchStrategy<T extends Comparable<T>> extends SearchStrategy<T> {
    public BinarySearchStrategy(List<T> records) {
        super(records);
    }

    @Override
    public boolean contains(T record) {
        return binarySearch(records, record, 0, records.size()  - 1);
    }

    private boolean binarySearch(List<T> records, T record, int left, int right) {
        if (left > right) {
            return false;
        }
        var mid = (left + right) >>> 1;
        var middleRecord = records.get(mid);
        if (middleRecord.compareTo(record) == 0) {
            return true;
        } else if (middleRecord.compareTo(record) > 0) {
            return binarySearch(records, record, left, mid - 1);
        } else {
            return binarySearch(records, record, mid + 1, right);
        }

    }
}
