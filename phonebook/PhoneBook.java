package phonebook;

import phonebook.algorithms.SearchStrategy;
import phonebook.algorithms.SortStrategy;

import javax.naming.TimeLimitExceededException;
import java.util.List;

public class PhoneBook {
    private final List<PhoneRecord> phoneRecords;
    private SearchStrategy<PhoneRecord> strategy;
    private SortStrategy<PhoneRecord> sortStrategy;

    public PhoneBook(List<PhoneRecord> phoneRecords) {
        this.phoneRecords = phoneRecords;
    }

    public void setStrategy(SearchStrategy<PhoneRecord> strategy) {
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


}
