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
        runBubbleSortWithLinearSearch(stopWatch.getElapsedMilliSeconds() * 10);
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
            sortTimeMessage = String.format("Sorting time: %s", stopWatch.getElapsedFormattedTime());
        } catch (TimeLimitExceededException exception) {
            sortTimeMessage = String.format("%s, moved to linear search", exception.getMessage());
            PHONE_BOOK.setStrategy(new LinearSearchStrategy<>());
        } finally {
            var searchStopWatch = new StopWatch();
            searchStopWatch.start();
            foundRecordsCount = runSearch();
            searchStopWatch.stop();
            searchTimeMessage = String.format("Searching time: %s", searchStopWatch.getElapsedFormattedTime());
        }
        stopWatch.stop();
        System.out.printf("Found %d / %d entries. Time taken: %s%n", foundRecordsCount,
                SEARCH_RECORDS.size(), stopWatch.getElapsedFormattedTime());
        System.out.println(sortTimeMessage);
        System.out.println(searchTimeMessage);
    }

    private static StopWatch runLinearSearch() {
        var stopWatch = new StopWatch();
        System.out.println("Start searching... (linear search)");
        stopWatch.start();
        PHONE_BOOK.setStrategy(new LinearSearchStrategy<>());
        var foundRecordsCount = runSearch();
        stopWatch.stop();
        System.out.printf("Found %d / %d entries.%n",
                foundRecordsCount, SEARCH_RECORDS.size());
        System.out.printf("Time taken: %s%n", stopWatch.getElapsedFormattedTime());
        return stopWatch;
    }

    private static long runSearch() {
        return SEARCH_RECORDS.stream().filter(PHONE_BOOK::personExists).count();
    }
}
