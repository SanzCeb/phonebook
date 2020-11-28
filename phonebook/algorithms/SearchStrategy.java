package phonebook.algorithms;

import java.util.List;

public interface SearchStrategy <T> {
    boolean match (List<T> records, T record);
}
