package phonebook.algorithms.sort;

import phonebook.algorithms.sort.SortStrategy;

import java.util.List;

public class QuickSortStrategy <T extends Comparable <T>> implements SortStrategy<T> {
    @Override
    public void sort(List<T> records) {
        if (records.size() > 1) {
            var pivotIndex = partition(records);
            sort(records.subList(0, pivotIndex));
            sort(records.subList(pivotIndex + 1, records.size()));
        }
    }

    private int partition(List<T> records) {
        var pivotIndex = records.size() - 1;
        var pivot = records.get(pivotIndex);
        var partitionIndex = 0;

        for (int i = 0; i < pivotIndex; i++) {
            if (records.get(i).compareTo(pivot) < 0) {
                swap(records, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap (records, partitionIndex, pivotIndex);

        return partitionIndex;
    }

    private void swap (List<T> records, int i1, int i2) {
        var temp = records.set(i1, records.get(i2));
        records.set(i2, temp);
    }
}
