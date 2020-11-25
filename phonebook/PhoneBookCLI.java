package phonebook;

import java.util.stream.Collectors;

public class PhoneBookCLI {
    public static void run() {
        var phoneRecords = PhoneBookInput.readPhoneBookRecords("directory.txt");
        var searchRecords = PhoneBookInput.readPersonRecords("find.txt");
        var phoneBook = new PhoneBook(phoneRecords);

        var foundRecords = searchRecords.stream().filter(phoneBook::personExists);

        System.out.println("Start searching...");
        var startTimestamp = System.currentTimeMillis();
        var foundRecordsCount = foundRecords.count();
        var endTimestamp = System.currentTimeMillis();

        System.out.printf("Found %d / %d entries. ", foundRecordsCount, searchRecords.size());
        var timeStamp = endTimestamp - startTimestamp;
        var minutes = timeStamp / 1_000_000;
        var seconds = timeStamp / 1000 - minutes * 1000;
        var milliseconds = timeStamp - minutes * 1_000_000 - seconds * 1000;

        System.out.printf("Time taken: %d min. %d sec. %d ms.", minutes, seconds, milliseconds);
    }
}
