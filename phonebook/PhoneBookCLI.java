package phonebook;

import phonebook.algorithms.BubbleSortStrategy;
import phonebook.algorithms.JumpSearchStrategy;
import phonebook.algorithms.LinearSearchStrategy;
import phonebook.stopwatch.StopWatch;

import javax.naming.TimeLimitExceededException;
import java.util.List;

public class PhoneBookCLI {
    private final static PhoneBook PHONE_BOOK;
    private final static List<PersonRecord> SEARCH_RECORDS;

    static {
        var dirFile  = "C:\\Users\\scebollero\\IdeaProjects\\Phone Book\\Phone Book\\task\\directory.txt";
        var findFile = "C:\\Users\\scebollero\\IdeaProjects\\Phone Book\\Phone Book\\task\\find.txt";
        PHONE_BOOK = new PhoneBook(PhoneBookInput.readPhoneBookRecords(dirFile));
        SEARCH_RECORDS = PhoneBookInput.readPersonRecords(findFile);
    }

    public static void run() {
        var stopWatch = runLinearSearch();
        runBubbleSortWithLinearSearch(stopWatch.getElapsedMilliSeconds());
    }

    private static void runBubbleSortWithLinearSearch(long elapsedMinutes) {
        PHONE_BOOK.setSortStrategy(new BubbleSortStrategy<>(elapsedMinutes));
        PHONE_BOOK.setStrategy(new JumpSearchStrategy<>());
        String sortTimeMessage;
        String searchTimeMessage;
        long foundRecordsCount;
        var stopWatch = new StopWatch();
        System.out.println("Start searching... (bubble sort + jump search)");
        stopWatch.start();
        try {
            PHONE_BOOK.sort();
            var elapsedMinutesPart = stopWatch.getInstantMinutesPart();
            var elapsedSeconds = stopWatch.getInstantSecondsPart();
            var elapsedMillis = stopWatch.getInstantMillisPart();
            var format = "Sorting time: %d min. %d sec. %d ms.";
            sortTimeMessage = String.format(
                    format,
                    elapsedMinutesPart,
                    elapsedSeconds,
                    elapsedMillis);
        } catch (TimeLimitExceededException exception) {
            sortTimeMessage = String.format("%s, moved to linear search", exception.getMessage());
            PHONE_BOOK.setStrategy(new LinearSearchStrategy<>());
        } finally {
            var searchStopWatch = new StopWatch();
            searchStopWatch.start();
            foundRecordsCount = runSearch();
            searchStopWatch.stop();
            var format = "Searching time: %d min. %d sec. %d ms.";
            searchTimeMessage = String.format(
                    format,
                    searchStopWatch.getElapsedMinutesPart()
                    , searchStopWatch.getElapsedSecondsPart()
                    , searchStopWatch.getElapsedMilliSecondsPart());
        }
        stopWatch.stop();
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.%n",
                foundRecordsCount,
                SEARCH_RECORDS.size()
                , stopWatch.getElapsedMinutesPart()
                , stopWatch.getElapsedSecondsPart()
                , stopWatch.getElapsedMilliSecondsPart());
        System.out.println(sortTimeMessage);
        System.out.println(searchTimeMessage);
    }

    public static StopWatch runLinearSearch() {
        var stopWatch = new StopWatch();
        System.out.println("Start searching... (linear search)");
        stopWatch.start();

        PHONE_BOOK.setStrategy(new LinearSearchStrategy<>());
        var foundRecordsCount = runSearch();
        stopWatch.stop();
        System.out.printf("Found %d / %d entries.%n",
                foundRecordsCount, SEARCH_RECORDS.size());
        var minutes = stopWatch.getElapsedMinutesPart();
        var seconds = stopWatch.getElapsedSecondsPart();
        var milliseconds = stopWatch.getElapsedMilliSecondsPart();
        System.out.printf("Time taken: %d min. %d sec. %d ms.%n", minutes, seconds, milliseconds);
        return stopWatch;
    }

    private static long runSearch() {
        return SEARCH_RECORDS.stream().filter(PHONE_BOOK::personExists).count();
    }
}
