package phonebook.algorithms.search;

import java.util.List;

public class LinearSearchStrategy <T> extends SearchStrategy<T> {

    public LinearSearchStrategy(List<T> records) {
        super(records);
    }

    public boolean contains(T record) {
        for (var currentRecord : records) {
            if (currentRecord.equals(record)) {
                return true;
            }
        }
        return false;
    }
}
