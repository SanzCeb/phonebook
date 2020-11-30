package phonebook.algorithms.sort;

import phonebook.algorithms.sort.SortStrategy;
import phonebook.stopwatch.StopWatch;

import javax.naming.TimeLimitExceededException;
import java.util.List;

public class BubbleSortStrategy <T extends Comparable<T>> implements SortStrategy<T> {

    private final long limit;

    public BubbleSortStrategy(long limit) {
        this.limit = limit;
    }

    @Override
    public void sort(List<T> records) throws TimeLimitExceededException {
        var stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                var currentRecord = records.get(j);
                var nextRecord = records.get(j + 1);
                if (currentRecord.compareTo(nextRecord) > 0) {
                    currentRecord = records.set(j, nextRecord);
                    records.set(j + 1, currentRecord);
                    if (stopWatch.getElapsedMilliSeconds() >= limit) {
                        throwTimeExceededException(stopWatch);
                    }
                }
            }
        }

    }

    private void throwTimeExceededException(StopWatch stopWatch) throws TimeLimitExceededException {
        stopWatch.stop();
        var elapsedMinutes = stopWatch.getElapsedMinutesPart();
        var elapsedSeconds = stopWatch.getElapsedSecondsPart();
        var elapsedMillis = stopWatch.getElapsedMilliSecondsPart();
        var format = "Sorting time: %d min. %d sec. %d ms. - STOPPED";
        var message = String.format(
                format,
                elapsedMinutes,
                elapsedSeconds,
                elapsedMillis);
        throw new TimeLimitExceededException(message);
    }
}
