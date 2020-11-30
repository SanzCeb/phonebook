package phonebook.algorithms.search;

import java.util.List;

public abstract class SearchStrategy <T> {
    protected final List<T> records;

    protected SearchStrategy(List<T> records) {
        this.records = records;
    }
    public abstract boolean contains(T record);
}
