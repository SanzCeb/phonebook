package phonebook.algorithms;

import java.util.List;

public class LinearSearchStrategy <T> implements SearchStrategy<T>{
    public boolean contains(List<T> records, T record) {
        for (var currentRecord : records) {
            if (currentRecord.equals(record)) {
                return true;
            }
        }
        return false;
    }
}
