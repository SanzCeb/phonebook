package phonebook.algorithms;

import javax.naming.TimeLimitExceededException;
import java.util.List;

public interface SortStrategy <T extends Comparable<T>> {
    void sort (List<T> records) throws TimeLimitExceededException;
}
