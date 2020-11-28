package phonebook.algorithms;

import java.util.List;

public class LinearSearchStrategy <T> implements SearchStrategy<T>{
    public boolean match(List<T> records, T record) {
        return records.stream().anyMatch(t -> t.equals(record));
    }
}
