package phonebook;

import java.util.List;

public class PhoneBook {
    private final List<PhoneRecord> phoneRecords;

    public PhoneBook(List<PhoneRecord> phoneRecords) {
        this.phoneRecords = phoneRecords;
    }

    public boolean personExists(PersonRecord personRecord) {
        return phoneRecords.stream().anyMatch(phoneRecord -> phoneRecord.matchPerson(personRecord));
    }


}
