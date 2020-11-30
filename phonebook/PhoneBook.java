package phonebook;

import phonebook.algorithms.search.SearchStrategy;
import phonebook.algorithms.sort.SortStrategy;

import javax.naming.TimeLimitExceededException;
import java.util.Collections;
import java.util.List;

public class PhoneBook {
    private final List<PhoneRecord> phoneRecords;
    private SearchStrategy<PhoneRecord> strategy;
    private SortStrategy<PhoneRecord> sortStrategy;

    public PhoneBook(List<PhoneRecord> phoneRecords) {
        this.phoneRecords = phoneRecords;
    }

    public void setSearchStrategy(SearchStrategy<PhoneRecord> strategy) {
        this.strategy = strategy;
    }

    public void setSortStrategy (SortStrategy<PhoneRecord> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort () throws TimeLimitExceededException {
        this.sortStrategy.sort(phoneRecords);
    }

    public boolean personExists(PersonRecord personRecord) {
        return strategy.contains(phoneRecords, new PhoneRecord(personRecord));
    }


    public void shuffle() {
        Collections.shuffle(phoneRecords);
    }
}
