package phonebook.algorithms.search;

import java.util.List;

public class JumpSearchStrategy<T extends Comparable<T>> extends SearchStrategy<T> {

    public JumpSearchStrategy(List<T> records) {
        super(records);
    }

    public boolean contains(T record) {
        if (records.isEmpty()) {
            return false;
        }

        if (records.get(0).equals(record)) {
            return true;
        }

        var recordsSize = records.size();
        var jumpLength = (int) Math.sqrt(recordsSize);


        if (records.get(jumpLength).equals(record)) {
            return true;
        }

        var rightIndex = 0;
        var leftIndex = 0;
        while (rightIndex < recordsSize - 1) {
            rightIndex = Math.min(recordsSize - 1, rightIndex + jumpLength);
            if (records.get(rightIndex).compareTo(record) >= 0) {
                break;
            }
            leftIndex = rightIndex;
        }

        if ((rightIndex == recordsSize - 1) &&
                record.compareTo(records.get(rightIndex)) > 0) {
            return false;
        }
        var subList = records.subList(leftIndex, rightIndex);
        return new LinearSearchStrategy<T>(subList).contains(record);
    }
}
