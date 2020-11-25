package phonebook;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBookInput {

    public static List<String> readInputFile(String filename) {
        var recordFile = new File(filename);
        List<String> recordBuilder = new ArrayList<>();
        try (var fileStream = new Scanner(recordFile)) {
            while (fileStream.hasNextLine()) {
                recordBuilder.add(fileStream.nextLine());
            }
        } catch (FileNotFoundException ignored) {
            System.out.println("File not Found!");
        }
        recordBuilder.removeIf(str -> str.equals("\n"));
        return  recordBuilder;
    }

    public static List<PhoneRecord> readPhoneBookRecords(String fileName) {
        return readInputFile(fileName).stream()
                .map(PhoneRecord::parsePhoneRecord)
                .collect(Collectors.toList());
    }

    public static List<PersonRecord> readPersonRecords(String filename) {
        return readInputFile(filename).stream()
                .map(PersonRecord::parsePersonRecord)
                .collect(Collectors.toList());
    }
}
