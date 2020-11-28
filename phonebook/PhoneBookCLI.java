package phonebook;

import phonebook.algorithms.BubbleSortStrategy;
import phonebook.algorithms.JumpSearchStrategy;
import phonebook.algorithms.LinearSearchStrategy;
import phonebook.algorithms.SearchStrategy;
import phonebook.stopwatch.StopWatch;

import javax.naming.TimeLimitExceededException;
import java.util.List;

public class PhoneBookCLI {
    private final static PhoneBook PHONE_BOOK;
    private final static List<PersonRecord> SEARCH_RECORDS;
    private final static SearchStrategy<PhoneRecord> LINEAR_SEARCH;
    private final static SearchStrategy<PhoneRecord> JUMP_SEARCH;

    static {
        var dirFile  = "C:\\Users\\scebollero\\IdeaProjects\\Phone Book\\Phone Book\\task\\directory.txt";
        var findFile = "C:\\Users\\scebollero\\IdeaProjects\\Phone Book\\Phone Book\\task\\find.txt";
        PHONE_BOOK = new PhoneBook(PhoneBookInput.readPhoneBookRecords(dirFile));
        SEARCH_RECORDS = PhoneBookInput.readPersonRecords(findFile);
        LINEAR_SEARCH = new LinearSearchStrategy<>();
        JUMP_SEARCH = new JumpSearchStrategy<>();
    }

    public static void run() {
        var elapsedMinutes = runSearch(LINEAR_SEARCH);
        try {
            var elapsedTime = runBubbleSortWithLinearSearch(elapsedMinutes);
        } catch (TimeLimitExceededException exception) {
            System.out.print(exception.getMessage());
            System.out.print(", moved to linear search");
            runSearch(LINEAR_SEARCH);
        }
    }

    private static long runBubbleSortWithLinearSearch(long elapsedMinutes)
            throws TimeLimitExceededException {
        PHONE_BOOK.setSortStrategy(new BubbleSortStrategy<>(elapsedMinutes));
        PHONE_BOOK.setStrategy(new JumpSearchStrategy<>());
        PHONE_BOOK.sort();
        return runSearch(JUMP_SEARCH);
    }

    public static long runSearch(SearchStrategy <PhoneRecord> searchStrategy) {
        var stopWatch = new StopWatch();
        System.out.println("Start searching...");
        stopWatch.start();

        PHONE_BOOK.setStrategy(searchStrategy);
        var foundRecordsCount = SEARCH_RECORDS.stream()
                .filter(PHONE_BOOK::personExists)
                .count();
        stopWatch.stop();

        System.out.printf("Found %d / %d entries. ",
                foundRecordsCount, SEARCH_RECORDS.size());
        var minutes = stopWatch.getElapsedMinutesPart();
        var seconds = stopWatch.getElapsedSecondsPart();
        var milliseconds = stopWatch.getElapsedMilliSecondsPart();
        System.out.printf("Time taken: %d min. %d sec. %d ms.%n", minutes, seconds, milliseconds);
        return stopWatch.getElapsedMilliSeconds();
    }
}
