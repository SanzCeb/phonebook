package phonebook;

import phonebook.stopwatch.StopWatch;

public class PhoneBookCLI {
    public static void run() {
        var phoneRecords = PhoneBookInput.readPhoneBookRecords("directory.txt");
        var searchRecords = PhoneBookInput.readPersonRecords("find.txt");
        var phoneBook = new PhoneBook(phoneRecords);
        var stopWatch = new StopWatch();

        System.out.println("Start searching...");
        stopWatch.start();
        var foundRecordsCount = searchRecords.stream()
                .filter(phoneBook::personExists)
                .count();
        stopWatch.stop();

        System.out.printf("Found %d / %d entries. ", foundRecordsCount, searchRecords.size());
        var minutes = stopWatch.getElapsedMinutes();
        var seconds = stopWatch.getElapsedSeconds();
        var milliseconds = stopWatch.getElapsedMilliSeconds();

        System.out.printf("Time taken: %d min. %d sec. %d ms.", minutes, seconds, milliseconds);
    }
}
