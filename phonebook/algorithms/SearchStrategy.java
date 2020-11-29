package phonebook.algorithms;

import java.util.List;

public interface SearchStrategy <T> {
    boolean contains(List<T> records, T record);
}
